package com.mindorks.framework.mvvm.custom.repository;

import com.mindorks.framework.mvvm.custom.firebase.FirebaseRtcDataSource;
import com.mindorks.framework.mvvm.custom.firebase.model.IceCandidateFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.model.SessionDescFirebaseData;
import com.mindorks.framework.mvvm.custom.prefs.RoomPrefDataSource;
import com.mindorks.framework.mvvm.custom.rtc.ConnectionRtcDataSource;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class ConnectionRepository {

    private final FirebaseRtcDataSource mFirebaseRtcDataSource;
    private final RoomPrefDataSource mRoomPrefDataSource;

    public ConnectionRepository(@NonNull FirebaseRtcDataSource firebaseRtcDataSource,
                                @NonNull RoomPrefDataSource roomPrefDataSource) {
        mFirebaseRtcDataSource = firebaseRtcDataSource;
        mRoomPrefDataSource = roomPrefDataSource;
    }

    public void insertIceCandidate(@NonNull String node,
                                   @NonNull IceCandidateFirebaseData iceCandidateFirebaseData) {
        String roomId = mRoomPrefDataSource.getRoomId();
        if (roomId == null)
            throw new IllegalStateException("Room Id is null");
        mFirebaseRtcDataSource.insertIceCandidate(node, mRoomPrefDataSource.getRoomId(), iceCandidateFirebaseData);
    }

    public void insertSdp(@NonNull String node, @NonNull SessionDescFirebaseData sessionDescFirebaseData) {
        String roomId = mRoomPrefDataSource.getRoomId();
        if (roomId == null)
            throw new IllegalStateException("Room Id is null");
        mFirebaseRtcDataSource.insertSdp(node, roomId, sessionDescFirebaseData);
    }

    public Observable<IceCandidateFirebaseData> addIceCandidateListener(@NonNull String node) {
        String roomId = mRoomPrefDataSource.getRoomId();
        if (roomId == null)
            return Observable.error(new IllegalStateException("Room Id is null"));
        return mFirebaseRtcDataSource.addIceCandidateListener(node, roomId);
    }

    public Observable<SessionDescFirebaseData> addSdpListener(@NonNull String node) {
        String roomId = mRoomPrefDataSource.getRoomId();
        if (roomId == null)
            return Observable.error(new IllegalStateException("Room Id is null"));
        return mFirebaseRtcDataSource.addSdpListener(node, roomId);
    }
}
