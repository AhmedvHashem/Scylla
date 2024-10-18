package com.hashem.android.view;

import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hashem.android.R;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * Created by Hashem.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntStream.range(5, 10).forEach(System.out::println);

        SparseArray<String> sparseArray = new SparseArray<>();

        Optional<String> optional = Optional.ofNullable(null);

        CompletableFuture<Void> awdawd = CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture");
        });

        awdawd.thenRun(() -> {
            System.out.println("CompletableFuture thenRun");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
