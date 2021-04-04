/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2basic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.legacy.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends AppCompatActivity {
    String[] mPermissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    List<String> mPermissionList = new ArrayList<String>();
    private final int mRequestCode = 100; //权限请求码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        requestAllPermission(); //申请所需权限
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance()) //用Camera2BasicFragment替换id为container中的Fragment
                    .commit();
        }
    }

    private void requestAllPermission()
    {
        for(int i = 0; i < mPermissions.length; i++)
        {
            if(ContextCompat.checkSelfPermission(this,mPermissions[i]) != PackageManager.PERMISSION_GRANTED)
            {
                mPermissionList.add(mPermissions[i]);
            }
        }
        if (mPermissionList.size() > 0) { //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, mPermissions, mRequestCode);
        }else{
            //说明权限都已经通过，可以做你想做的事情去
        }
    }
}
