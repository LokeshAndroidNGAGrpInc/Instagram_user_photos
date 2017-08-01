package com.dev.pyar;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev.pyar.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUserPhotosActivity extends AppCompatActivity implements View.OnClickListener,PhotsFragment.OnFragmentInteractionListener {
    private Button btShowPhotos,btSelectPhoto;
    String TAG = "GetUserPhotos****";
    private ViewPager viewPagerPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_photos);
        btShowPhotos = (Button) findViewById(R.id.user_photos_showimages_bt);
        btShowPhotos.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_photos_showimages_bt:
                mCallAPIforGetImages();
                break;

        }
    }

    private void mCallAPIforGetImages() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StringConstants.INSTAGRAM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceOperations service = retrofit.create(ServiceOperations.class);
        Call<PhotoModel> photoModelCall =  service.getUserPhotos(StringConstants.mClientId,Singleton.getAccessToken());
        photoModelCall.enqueue(new Callback<PhotoModel>() {
            @Override
            public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                PhotoModel photoModel = response.body();
                if (photoModel != null) {
                    Toast.makeText(GetUserPhotosActivity.this,
                            photoModel.getDataList().get(0).getImages().getStandardResolution().getUrl(),
                            Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < photoModel.getDataList().size(); i++) {
                        Log.d(TAG, "onResponse: "+photoModel.getDataList().get(i).getImages().getStandardResolution().getUrl());
                    }
                    mShowImagesInDialog(photoModel);
                } else {
                    Toast.makeText(GetUserPhotosActivity.this, "Error retrieving images !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhotoModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(GetUserPhotosActivity.this, "Retro error!"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mShowImagesInDialog(PhotoModel photoModel) {
       /* final Dialog dialog = new Dialog(this);
        ViewGroup view  = (ViewGroup) LayoutInflater.from(GetUserPhotosActivity.this).inflate(R.layout.phots_layout,null);
        dialog.setContentView(view);
        dialog.show();
*/
        viewPagerPhotos = (ViewPager) findViewById(R.id.getuserphotos_viewpager);
        btSelectPhoto  = (Button) findViewById(R.id.get_userphotos_bt);
        List<String> photosList  = getPhotosList(photoModel);

        InstaPhotoAdapter photoAdapter = new InstaPhotoAdapter(getSupportFragmentManager(),photosList);
        viewPagerPhotos.setAdapter(photoAdapter);

        btSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private List<String> getPhotosList(PhotoModel photoModel) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < photoModel.getDataList().size(); i++) {
            arrayList.add(photoModel.getDataList().get(i).getImages().getStandardResolution().getUrl());
        }
        return arrayList;
    }




    public class InstaPhotoAdapter extends FragmentStatePagerAdapter{
        private List<String> photosList = null;
        public InstaPhotoAdapter(FragmentManager fm, List<String> photosList) {
            super(fm);
            this.photosList = photosList;
        }

        @Override
        public Fragment getItem(int position) {
            return PhotsFragment.newInstance(photosList.get(position));
        }

        @Override
        public int getCount() {
            return photosList.size();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
