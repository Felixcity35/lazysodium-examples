package com.goterl.lazycode.lazysodium.example.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.goterl.lazycode.lazysodium.example.R;
import com.goterl.lazycode.lazysodium.example.activities.*;
import com.goterl.lazycode.lazysodium.example.adapters.MultiAdapter;
import com.goterl.lazycode.lazysodium.example.models.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationsFragment extends BaseFragment {

    private MultiAdapter adapter;

    public static OperationsFragment newInstance() {
        OperationsFragment fragment = new OperationsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_operations, container, false);
        overlay = v.findViewById(R.id.overlay);
        adapter = new MultiAdapter(getActivity(), getListOfOps(), false);

        adapter.setClickListener(new MultiAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    openActivity(view, SymmetricEncryptionActivity.class);
                }
                if (position == 1) {
                    openActivity(view, AsymmetricEncryptionActivity.class);
                }
                if (position == 2) {
                    openActivity(view, GenericHashActivity.class);
                }
                if (position == 3) {
                    openActivity(view, PasswordHashActivity.class);
                }
                if (position == 4) {
                    openActivity(view, KeyDerivationActivity.class);
                }
            }
        });

        createRecycler(v);

        return v;
    }

    private void createRecycler(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    private List<Operation> getListOfOps() {
        List<Operation> operationList = new ArrayList<>();
        Operation symmetricEnc = new Operation(
                "Symmetric encryption",
                "Encryption using a symmetric key."
        );
        Operation asymmetricEnc = new Operation(
                "Asymmetric encryption",
                "Encryption using an asymmetric (public-private) key."
        );
        Operation genericHashing = new Operation(
                "Generic hashing",
                "Hash something with the Blake2B algorithm."
        );
        Operation passwordHashing = new Operation(
                "Password hashing",
                "Securely hash passwords."
        );
        Operation keyDerivation = new Operation(
                "Key derivation",
                "Derive keys from a master key."
        );
        operationList.add(symmetricEnc);
        operationList.add(asymmetricEnc);
        operationList.add(genericHashing);
        operationList.add(passwordHashing);
        operationList.add(keyDerivation);
        return operationList;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (overlay.getVisibility() == View.VISIBLE) {
            overlay.setVisibility(View.GONE);
        }
    }
}