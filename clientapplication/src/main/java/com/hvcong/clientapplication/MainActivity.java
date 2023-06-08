package com.hvcong.clientapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.hvcong.aidldata.Account;
import com.hvcong.aidldata.IAidlExample;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnBindService, mBtnGetNumber, mBtnSetNumber, mBtnGetAccount, mBtnSetAccount, mBtnGetListAccount, mBtnSetListAccount;
    private TextView mTxtNumber;
    private EditText mEditTextInputNumber;
    private IAidlExample mIAidlData;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIAidlData = IAidlExample.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIAidlData = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnBindService = findViewById(R.id.btn_bind_service);
        mBtnBindService.setOnClickListener(this);
        mBtnGetNumber = findViewById(R.id.btn_get_number);
        mBtnGetNumber.setOnClickListener(this);
        mBtnSetNumber = findViewById(R.id.btn_set_number);
        mBtnSetNumber.setOnClickListener(this);
        mBtnGetAccount = findViewById(R.id.btn_get_account);
        mBtnGetAccount.setOnClickListener(this);
        mBtnSetAccount = findViewById(R.id.btn_set_account);
        mBtnSetAccount.setOnClickListener(this);
        mBtnGetListAccount = findViewById(R.id.btn_get_list_account);
        mBtnGetListAccount.setOnClickListener(this);
        mBtnSetListAccount = findViewById(R.id.btn_set_list_account);
        mBtnSetListAccount.setOnClickListener(this);
        mTxtNumber = findViewById(R.id.txt_number);
        mEditTextInputNumber = findViewById(R.id.edt_input_number);
    }

    private void connectService() {
        if (null == mIAidlData) {
            Intent intent = new Intent(IAidlExample.class.getName());
            intent.setAction("service.aidl.communication");
            intent.setPackage("com.hvcong.aidlexample");
            bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_service:
            default:
                connectService();
                break;
            case R.id.btn_get_number:
                if (null != mIAidlData) {
                    try {
                        String number = String.valueOf(mIAidlData.getNumber());
                        mTxtNumber.setText(number);
                        Toast.makeText(this, "Get Number Finished", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_number:
                if (null != mIAidlData) {
                    try {
                        int number = Integer.parseInt(mEditTextInputNumber.getText().toString());
                        mIAidlData.sendNumber(number);
                        Toast.makeText(this, "Set Number Finished", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_account:
                if (null != mIAidlData) {
                    try {
                        Account account = mIAidlData.getAccount();
                        if (null != account) {
                            String userName = account.getUserName();
                            String passWord = account.getPassWord();
                            Toast.makeText(this, "UserName: " + userName + " / Password: " + passWord, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Account is null", Toast.LENGTH_SHORT).show();
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.btn_set_account:
                if (null != mIAidlData) {
                    try {
                        String userName = "username client";
                        String passWord = "password client";
                        Account account = new Account(userName, passWord, 0);
                        mIAidlData.setAccount(account);
                        Toast.makeText(this, "Set account completed", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_get_list_account:
                if (null != mIAidlData) {
                    try {
                        List<Account> listAccount = mIAidlData.getListAccount();
                        if (null != listAccount && listAccount.size() > 0) {
                            for (Account account: listAccount) {
                                Toast.makeText(this, "UserName: " + account.getUserName() + " / Password: " + account.getPassWord(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "list account hasn't account", Toast.LENGTH_SHORT).show();
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_set_list_account:
                if (null != mIAidlData) {
                    try {
                        String userName1 = "username1 client";
                        String passWord1 = "password1 client";
                        Account account1 = new Account(userName1, passWord1, 1);

                        String userName2 = "username2 client";
                        String passWord2 = "password2 client";
                        Account account2 = new Account(userName2, passWord2, 2);

                        List<Account> listAccount = new ArrayList<>();
                        listAccount.add(account1);
                        listAccount.add(account2);
                        mIAidlData.setListAccount(listAccount);

                        Toast.makeText(this, "Set list account completed", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "mIAidlData hasn't data", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}