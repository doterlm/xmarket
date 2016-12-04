package me.jcala.xmarket.mvp.school;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerSchoolComponent;
import me.jcala.xmarket.di.modules.SchoolModule;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.mvp.user.trades.add.TradeAddActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;


public class SchoolFragment extends BaseFragment implements SchoolView{
    @Inject
    protected SchoolPresenter presenter;

    private Unbinder unbinder;

    @BindView(R.id.school_deal_list)
    protected RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.school_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DaggerSchoolComponent.builder().schoolModule(new SchoolModule(getActivity(),this)).build().inject(this);
        presenter.getSchoolDealAgency();
    }


    @Override
    public void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick(R.id.school_deal_plus)
     void jumpTradeAddActivity(){
        Intent intent=new Intent(getActivity(), TradeAddActivity.class);
        startActivity(intent);
    }

}