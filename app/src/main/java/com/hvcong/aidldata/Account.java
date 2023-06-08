package com.hvcong.aidldata;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Account implements Parcelable {
    private String userName;
    private String passWord;
    private int status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account(String userName, String passWord, int status) {
        this.userName = userName;
        this.passWord = passWord;
        this.status = status;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    protected Account(Parcel in) {
        userName = in.readString();
        passWord = in.readString();
        status = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(passWord);
        parcel.writeInt(status);
    }
}
