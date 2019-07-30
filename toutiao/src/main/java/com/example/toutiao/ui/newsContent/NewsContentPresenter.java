package com.example.toutiao.ui.newsContent;

import com.example.base.net.RetrofitFactory;
import com.example.base.utils.ErrorAction;
import com.example.toutiao.api.INewsApi;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;
import com.example.toutiao.entity.news.NewsContentBean;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is NewsContentPresenter
 */
public class NewsContentPresenter implements INewsContentContract.Presenter {

    private NewsContentActivity mView;

    private String html;

    public NewsContentPresenter(NewsContentActivity view) {
        mView = view;
    }

    @Override
    public void doLoadData(MultiNewsArticleDataBean bean) {
        //mView.onSetWeb(bean.getDisplay_url());

        final String url = bean.getDisplay_url();

        Observable
                .create((ObservableOnSubscribe<String>) e -> {
                    String api = url.replace("www.", "")
                            .replace("toutiao", "m.toutiao")
                            .replace("group/", "i") + "info/";
                    e.onNext(api);
                })
                .subscribeOn(Schedulers.io())
                .switchMap((Function<String, ObservableSource<NewsContentBean>>) s -> RetrofitFactory.getInstance().create(INewsApi.class).getNewsContent(s))
                .map(this::getHTML)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.autoRxLifeCycle().bindToLifecycle())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        mView.onSetWeb(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.onSetWeb(null);
                        ErrorAction.print(e);
                    }

                    @Override
                    public void onComplete() {
                        doShowNetError();
                    }
                });

    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {
        mView.onHideLoading();
        mView.onShowNetError();
    }


    private String getHTML(NewsContentBean bean) {
        String title = bean.getData().getTitle();
        String content = bean.getData().getContent();
        if (content != null) {

            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/toutiao_light.css\" type=\"text/css\">";


            html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">" +
                    css +
                    "<body>\n" +
                    "<article class=\"article-container\">\n" +
                    "    <div class=\"article__content article-content\">" +
                    "<h1 class=\"article-title\">" +
                    title +
                    "</h1>" +
                    content +
                    "    </div>\n" +
                    "</article>\n" +
                    "</body>\n" +
                    "</html>";

            return html;
        } else {
            return null;
        }
    }
}


