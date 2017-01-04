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
public class RetrofitFragment1 extends SupportFragment implements View.OnClickListener {

    LayoutNullBinding binding;

    public static RetrofitFragment1 newInstance() {
        return new RetrofitFragment1();
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
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);

        Call<TestModel> model = service.testGet2("aaa", "gavinxxxxxx");

        model.enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                L.e(response.code());
                L.e(response.isSuccessful());

                if (response.isSuccessful()) {
                    L.e(response.body().getLogin());
                } else {
                    L.e(response.message());
                }
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
                L.e(t.getMessage());
            }
        });
    }

}
