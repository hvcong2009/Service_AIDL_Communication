package com.hvcong.aidlexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.hvcong.aidldata.Account;
import com.hvcong.aidldata.IAidlExample;
import java.util.List;

public class ExampleAIDLService extends Service {

    private int mNumber = 100;
    private Account mAccount;
    private List<Account> mListAccount;
    private final IAidlExample.Stub mBinder = new IAidlExample.Stub() {
    @Override
    public int getNumber() throws RemoteException {
        return mNumber;
    }

    @Override
    public Account getAccount() throws RemoteException {
        return mAccount;
    }

    @Override
    public List<Account> getListAccount() throws RemoteException {
        return mListAccount;
    }

    @Override
    public void sendNumber(int num1) throws RemoteException {
        mNumber = num1;
    }

    @Override
    public void setAccount(Account account) throws RemoteException {
        mAccount = account;
    }

    @Override
    public void setListAccount(List<Account> listAccount) throws RemoteException {
        mListAccount = listAccount;
    }
};

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
