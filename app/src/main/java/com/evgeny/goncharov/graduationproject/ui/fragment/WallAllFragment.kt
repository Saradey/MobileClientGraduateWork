package com.evgeny.goncharov.graduationproject.ui.fragment

import android.arch.paging.PagedList
import android.support.v7.widget.LinearLayoutManager
import com.evgeny.goncharov.graduationproject.R
import com.evgeny.goncharov.graduationproject.common.MainThreadExecutor
import com.evgeny.goncharov.graduationproject.model.view.ArticleView
import com.evgeny.goncharov.graduationproject.mvp.contract.WallAllContract
import com.evgeny.goncharov.graduationproject.mvp.presenter.WallAllPresenter
import com.evgeny.goncharov.graduationproject.ui.adapters.WallAdapter
import com.evgeny.goncharov.graduationproject.ui.adapters.source.PositionalDataSourceWallAll
import com.evgeny.goncharov.graduationproject.ui.adapters.utils.DiffUtilCallback
import com.evgeny.goncharov.graduationproject.ui.fragment.flow.contract.WallFlowContract
import kotlinx.android.synthetic.main.fragment_wall_all.*
import java.util.concurrent.Executors


/**
 * Created by Evgeny Goncharov on 2019-04-01.
 * jtgn@yandex.ru
 */


class WallAllFragment : BaseWallFragment(), WallAllContract.WallAllView {

    lateinit var presenter: WallAllContract.WallAllPresenter

    companion object {
        fun getInstance(wallFlowContract: WallFlowContract): BaseWallFragment {
            val wallAllFragment = WallAllFragment()
            wallAllFragment.wallFlowContract = wallFlowContract
            return wallAllFragment
        }
    }



    override fun initPagerAdapter(): PagedList<ArticleView> {
        val dataSource = PositionalDataSourceWallAll(presenter)
        return PagedList.Builder(dataSource, getConfigPagerAdapter())
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }



    override fun initPresenter() {
        presenter = WallAllPresenter(this)
    }


    override fun getTitle(): Int {
        return R.string.title_wall_str
    }

}