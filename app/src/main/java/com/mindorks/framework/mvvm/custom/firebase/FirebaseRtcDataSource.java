package com.mindorks.framework.mvvm.custom.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.dao.HangupFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.dao.IceCandidateFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.dao.SdpFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.model.HangupFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.model.IceCandidateFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.model.SessionDescFirebaseData;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class FirebaseRtcDataSource {

    @NonNull private final IceCandidateFirebaseDao iceCandidateFirebaseDao;
    @NonNull private final SdpFirebaseDao sdpFirebaseDao;
    @NonNull private final HangupFirebaseDao hangupFirebaseDao;

    @NonNull private final DatabaseReference databaseReference;

    public FirebaseRtcDataSource(@NonNull IceCandidateFirebaseDao iceCandidateFirebaseDao,
                                 @NonNull SdpFirebaseDao sdpFirebaseDao,
                                 @NonNull HangupFirebaseDao hangupFirebaseDao) {
        this.iceCandidateFirebaseDao = iceCandidateFirebaseDao;
        this.sdpFirebaseDao = sdpFirebaseDao;
        this.hangupFirebaseDao = hangupFirebaseDao;

        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void insertIceCandidate(@NonNull String node,
                                   @NonNull String roomId,
                                   @NonNull IceCandidateFirebaseData iceCandidateFirebaseData) {
        iceCandidateFirebaseDao.insertIceCandidate(databaseReference.child(roomId).child(node),
                iceCandidateFirebaseData);
    }

    public Observable<IceCandidateFirebaseData> addIceCandidateListener(@NonNull String node, @NonNull String roomId) {
        return iceCandidateFirebaseDao.addIceCandidateListener(databaseReference.child(roomId).child(node));
    }

    public void insertSdp(@NonNull String node,
                          @NonNull String roomId,
                          @NonNull SessionDescFirebaseData sessionDescFirebaseData) {
        sdpFirebaseDao.insertSdp(databaseReference.child(roomId).child(node), sessionDescFirebaseData);
    }

    public Observable<SessionDescFirebaseData> addSdpListener(@NonNull String node, @NonNull String roomId) {
        return sdpFirebaseDao.addSdpListener(databaseReference.child(roomId).child(node));
    }

    public void updateHangupStatus(@NonNull String node,
                                   @NonNull String roomId,
                                   @NonNull HangupFirebaseData hangupFirebaseData) {
        hangupFirebaseDao.updateHangupFlag(databaseReference.child(roomId).child(node), hangupFirebaseData);
    }

    public Observable<HangupFirebaseData> addHangupListener(@NonNull String node, @NonNull String roomId) {
        return hangupFirebaseDao.addHangupListener(databaseReference.child(roomId).child(node));
    }
}
