package com.gavin.demo.features.usage.retrofit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.LayoutNullBinding;
import com.gavin.demo.utils.L;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 用处示例
 *
 * @author gavin.xiong 2016/11/10
 */
public class RetrofitFragment2 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static RetrofitFragment2 newInstance() {
        return new RetrofitFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_null, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        get();
        post();
    }

    private void get() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://www.tnomg.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);

//        Call<ApiResult<User>> model = service.testGetUserInfo(3215L);
//        Call<ApiResult<User>> model = service.testGetUserInfo("0L");
        Call<ApiResult<User>> model = service.testGetUserInfo("L");

        model.enqueue(new Callback<ApiResult<User>>() {
            @Override
            public void onResponse(Call<ApiResult<User>> call, Response<ApiResult<User>> response) {
                L.e(response.code());
                L.e(response.isSuccessful());

                if (response.isSuccessful()) {
                    L.e(response.body());
                } else {
                    L.e(response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResult<User>> call, Throwable t) {
                L.e(t.getMessage());
            }
        });
    }

    private void post() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://www.tnomg.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);

//        Call<ApiResult<User>> model = service.testGetUserInfo(3215L);
//        Call<ApiResult<User>> model = service.testGetUserInfo("0L");
//        Call<ApiResult<User>> model = service.testGetUserInfo("L");

        UpdateUserNameBody body = new UpdateUserNameBody();
        body.setUid(2277L);
        body.setNickName("gavinxxxxxx");
        body.setOldNickName("gavinxxxxxx.");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjc3IiwiaWF0IjoxNDgwOTI4MTM5LCJzdWIiOiJhcHAiLCJpc3MiOiJhcHAiLCJqd3RfbG9naW5fdXNlciI6eyJpZCI6MjI3NywiYXBwVmVyc2lvbiI6IjIuMC42IiwiY2hhbm5lbElkIjoiMzU0MzYwMDcwMDk4NzkzIiwiZGV2aWNlVHlwZSI6MSwiYWNjb3VudCI6IjE4NTIwNzc2NjM0IiwicGhvbmUiOiIxODUyMDc3NjYzNCIsIm1lcmNoYW50SWQiOm51bGwsInByb2plY3ROYW1lIjoiYXBwIiwiand0U3ViamVjdCI6ImFwcCIsInJhbmRvbVN0ciI6IlNEQjVTbmRTYVRCMU4yNURjM0pXZFVsNFpVSllOR0pCV2pCRmVIQTVhMHRwWlVsNGNsWjFlVmgyT0QxZk1qSTNOdz09In19.fhV0jVHehJS8qVFriHUlNreMsM6ZSkpsGEl0dUrd57Q";
        Call<ApiResult<String>> model = service.testPostUpdateNickName(token, body);

        model.enqueue(new Callback<ApiResult<String>>() {
            @Override
            public void onResponse(Call<ApiResult<String>> call, Response<ApiResult<String>> response) {
                L.e(response.code());
                L.e(response.isSuccessful());

                if (response.isSuccessful()) {
                    L.e(response.body());
                } else {
                    L.e(response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResult<String>> call, Throwable t) {
                L.e(t.getMessage());
            }
        });
    }

}
