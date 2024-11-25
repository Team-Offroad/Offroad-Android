package com.teamoffroad.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.teamoffroad.core.common.domain.model.NotificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun navigateToAnnouncement(announcementId: String) {
        _mainUiState.value = _mainUiState.value.copy(
            announcementId = announcementId
        )
    }

    fun initState() {
        _mainUiState.value = _mainUiState.value.copy(
            announcementId = null,
        )
    }

    var asd = MutableStateFlow("")

    init {
        //아까 CharacterChatBroadcastReceiver에서 게시한 브로드캐스트리시버를 여기서 받습니다.
        EventBus.getDefault().register(this)
    }

    //뷰모델이 삭제될때 이벤트버스도 해제시켜줍니다.
    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    //브로드캐스트리시버가 작동할때마다 동작하는 함수(fcm발송 > 앱이 포그라운드에 있고, 타입이 캐릭터채팅이라면 작동)
    //그런데 홈화면이 아니고 다른화면에서 이 함수가 호출되면 ui가 활성되있지 않기 때문에 ui작업을 할 수 없습니다.(함수 실행될때 로그는 찍힘)
    //그래서 데이터스토어 같은 로컬저장소에 데이터와 캐릭터 채팅확인 여부를 저장해두었다가
    //홈화면에 들어와서 채팅확인 여부가 x라면 알림을 보여주고, 알림을 봤다면 다시 채팅확인 여부가 o로 만드는식으로 하면 될거같습니다.
    //그래서 포스트맨으로 fcm쏴보면서 요함수에서 하면 될 것 같습니다.
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNotificationEvent(event: NotificationEvent) {
        Log.d("characterChat data", event.toString())
    }
}