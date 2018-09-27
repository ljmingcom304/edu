package org.ljming.edu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

/**
 * Title: SourceFragment
 * <p>
 * Description:
 * </p>
 * Author: Jming.L
 * Date: 2018/9/27 19:30
 * Copyright: Jming.Liang All rights reserved.
 */
public class SourceFragment extends Fragment {

    private RecyclerView mRvItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_source,null);
        mRvItem = (RecyclerView) view.findViewById(R.id.rv_item);
        mRvItem.setLayoutManager(new GridLayoutManager(getContext(),6));
        return view;
    }

    public void setFile(File file){

    }



}
