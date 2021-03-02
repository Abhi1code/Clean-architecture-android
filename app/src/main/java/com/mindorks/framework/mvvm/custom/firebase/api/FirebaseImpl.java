package com.mindorks.framework.mvvm.custom.firebase.api;

import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.framework.mvvm.custom.firebase.dao.HangupFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.dao.IceCandidateFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.dao.SdpFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.rx.BaseRxFirebase;
import com.mindorks.framework.mvvm.custom.rtc.dao.MediaDao;
import com.mindorks.framework.mvvm.custom.rtc.source.MediaDaoImpl;

import androidx.annotation.NonNull;

public class FirebaseImpl {

    private volatile IceCandidateFirebaseDao iceCandidateFirebaseDao;
    private volatile SdpFirebaseDao sdpFirebaseDao;
    private volatile HangupFirebaseDao hangupFirebaseDao;

    @NonNull private final BaseRxFirebase baseRxFirebase;

    public FirebaseImpl(@NonNull BaseRxFirebase baseRxFirebase) {
        this.baseRxFirebase = baseRxFirebase;
    }

    public IceCandidateFirebaseDao iceCandidateFirebaseDao() {
        if (iceCandidateFirebaseDao != null) {
            return iceCandidateFirebaseDao;
        } else {
            synchronized (this) {
                if (iceCandidateFirebaseDao == null) {
                    iceCandidateFirebaseDao = new IceCandidateFirebaseDaoImpl(baseRxFirebase);
                }
                return iceCandidateFirebaseDao;
            }
        }
    }

    public SdpFirebaseDao sdpFirebaseDao() {
        if (sdpFirebaseDao != null) {
            return sdpFirebaseDao;
        } else {
            synchronized (this) {
                if (sdpFirebaseDao == null) {
                    sdpFirebaseDao = new SdpFirebaseDaoImpl(baseRxFirebase);
                }
                return sdpFirebaseDao;
            }
        }
    }

    public HangupFirebaseDao hangupFirebaseDao() {
        if (hangupFirebaseDao != null) {
            return hangupFirebaseDao;
        } else {
            synchronized (this) {
                if (hangupFirebaseDao == null) {
                    hangupFirebaseDao = new HangupFirebaseDaoImpl(baseRxFirebase);
                }
                return hangupFirebaseDao;
            }
        }
    }
}
