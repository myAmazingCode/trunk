package net.shopnc.b2b2c.android.ui.good;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.LineBreakLayout;
import net.shopnc.b2b2c.android.custom.dialog.SearchSortDialog;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodActivity extends Activity {

    //    @Bind(R.id.llSort)
//    LinearLayout llSort;
    @Bind(R.id.etSearchText)
    EditText etSearchText;
    @Bind(R.id.btnSearch)
    Button btnSearch;
    @Bind(R.id.lblHotSearch)
    LineBreakLayout lblHotSearch;
    @Bind(R.id.lblHistory)
    LineBreakLayout lblHistory;
    @Bind(R.id.tvSortName)
    TextView tvSortName;
    @Bind(R.id.tvHistory)
    TextView tvHistory;
    @Bind(R.id.ivSearch)
    ImageView ivSearch;
    @Bind(R.id.btnClearHistory)
    Button btnClearHistory;
    @Bind(R.id.llChoosens)
    ListView llChoosens;
    @Bind(R.id.ivSearchDelete)
    ImageView ivSearchDelete;
//    @Bind(R.id.searchFlexboxLayout)
//    FlexboxLayout searchFlexboxLayout;
//    @Bind(R.id.historyFlexboxLayout)
//    FlexboxLayout historyFlexboxLayout;

    private MyShopApplication application;
    private Context context;
    private String keyWord;
    private String showWord;
    private List<String> hotSearchs;
    private List<String> historys;
    private SearchSortDialog sortDialog;
    private List<String> termList;
    private CommonAdapter<String> adapter;
    private int promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_search_good);
        ButterKnife.bind(this);
        application = MyShopApplication.getInstance();
        context = this;
        showWord = getIntent().getStringExtra("showWord");
        keyWord = getIntent().getStringExtra("keyword");
        etSearchText.setHint(showWord);
        etSearchText.clearFocus();
        sortDialog = new SearchSortDialog(context, tvSortName, tvSortName);
        termList = new ArrayList<>();
        initAdapter();
        etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Common.isEmpty(s)) {
                    llChoosens.setVisibility(View.GONE);
                    ivSearchDelete.setVisibility(View.GONE);
                } else {
                    if (Common.getText(tvSortName).equals("商品")) {
                        requestSuggestData(s.toString());
                    }
                    ivSearchDelete.setVisibility(View.VISIBLE);
                }
            }
        });

        getHotDatas();

        //为推广搜索
        initPromotion();
    }

    private void initPromotion() {
        promotion = getIntent().getIntExtra("promotion", 0);
        if (promotion == -1) {

            tvSortName.setText("推广");
            ivSearch.setVisibility(View.GONE);
            tvHistory.setVisibility(View.GONE);
            lblHistory.setVisibility(View.GONE);
            btnClearHistory.setVisibility(View.GONE);
            etSearchText.setHint("请输入搜索关键词");
        }
    }

    private void initAdapter() {
        adapter = new CommonAdapter<String>(context, R.layout.listview_termlist_item) {
            @Override
            public void convert(ViewHolder holder, final String s) {
                holder.setText(R.id.tvName, s);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llChoosens.setVisibility(View.GONE);
                        HashMap<String, String> params = new HashMap<>();
                        params.put("keyword", s);
                        saveKeyword(s);
                        Common.gotoActivity(SearchGoodActivity.this, SearchGoodsShowActivity.class, false, params);
                    }
                });
            }
        };
        llChoosens.setAdapter(adapter);
    }

    private void requestSuggestData(final String term) {
        OkHttpUtil.getAsyn(this, ConstantUrl.URL_SUGGEST_TERM + "?term=" + term, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                if (JsonUtil.toInteger(response, "code") == 200) {
                    termList = JsonUtil.toBean(response, "datas", "suggestList", new TypeToken<ArrayList<String>>() {
                    }.getType());
                    if (termList.size() == 0) {
                        llChoosens.setVisibility(View.GONE);
                    } else {
                        llChoosens.setVisibility(View.VISIBLE);
                    }

                    adapter.setmDatas(termList);
                    adapter.notifyDataSetChanged();
                } else {
                    llChoosens.setVisibility(View.GONE);
                }

            }
        });
    }

    private void getHistoryDatas() {
        historys = application.getSearchKeyList();
        Collections.reverse(historys);
        bindHistoryDatas(historys);
    }

    private void bindHistoryDatas(List<String> historys) {
        lblHistory.removeAllViews();
        if (historys == null || historys.isEmpty()) {
            return;
        }
        for (final String s : historys) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.searchgood_history_flexbox_single);
            addViewHolder.setText(R.id.btnContent, s);
            lblHistory.addView(addViewHolder.getCustomView());
            addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGoodSelect(s);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("keyword", s);
                    Common.gotoActivity(SearchGoodActivity.this, SearchGoodsShowActivity.class, false, params);
                }
            });
        }
    }

    private void getHotDatas() {
        hotSearchs = new ArrayList<>();
        OkHttpUtil.getAsyn(this, ConstantUrl.URL_SEARCH_DEFAULT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                hotSearchs = JsonUtil.toBean(data, "keywordList", new TypeToken<ArrayList<String>>() {
                }.getType());
                initHotSearch();
            }
        });
    }

    private void initHotSearch() {
        for (final String s : hotSearchs) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.searchgood_flexbox_single);
            addViewHolder.setText(R.id.btnContent, s);
            lblHotSearch.addView(addViewHolder.getCustomView());
            addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGoodSelect(s);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("keyword", s);
                    saveKeyword(s);
                    Common.gotoActivity(SearchGoodActivity.this, SearchGoodsShowActivity.class, false, params);
                }
            });
        }
    }

    private void setGoodSelect(String s) {
        tvSortName.setText("商品");
        etSearchText.setText(s);
    }

    @OnClick({R.id.llSort, R.id.btnSearch, R.id.btnClearHistory, R.id.btnBack, R.id.ivSearchDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llSort:
                sortDialog.show();
                break;
            case R.id.btnSearch:
                if (promotion == -1) {
                    //推广搜索
                    Intent data = new Intent();
                    data.putExtra("keyword", etSearchText.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    String key = Common.getText(etSearchText);
                    if (Common.getText(tvSortName).equals("商品")) {
                        if (key.equals("")) {
                            key = keyWord;
                        } else {
                            saveKeyword(key);
                        }
                        //搜索结果页
                        HashMap<String, String> params = new HashMap<>();
                        params.put("keyword", key);
                        Common.gotoActivity(this, SearchGoodsShowActivity.class, false, params);
                    } else {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("keyword", key);
                        Common.gotoActivity(this, SearchStoresShowActivity.class, false, params);
                    }
                }
                break;
            case R.id.btnClearHistory:
                application.getSearchKeyList().clear();
                lblHistory.removeAllViews();
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.ivSearchDelete:
                etSearchText.setText("");
                break;
        }
    }


    private void saveKeyword(String text) {
        if (text.trim().equals("")) {
            return;
        }
        Collections.reverse(historys);
        if (historys.contains(text)) {
            application.getSearchKeyList().remove(text);
        }
        application.getSearchKeyList().add(text);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getHistoryDatas();

    }

}
