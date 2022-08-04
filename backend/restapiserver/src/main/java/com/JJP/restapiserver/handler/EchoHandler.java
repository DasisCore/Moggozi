package com.JJP.restapiserver.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.JJP.restapiserver.domain.entity.member.Member;
import com.JJP.restapiserver.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class EchoHandler extends TextWebSocketHandler {

    private final MemberService memberService;
//    public EchoHandler(){};
    //로그인 한 전체
    List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    // 1대1
    Map<Long, WebSocketSession> userSessionsMap = new HashMap<Long, WebSocketSession>();

    //서버에 접속이 성공 했을때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("연결 됐음");
        Long senderId = getId(session);
        userSessionsMap.put(senderId , session);
    }

    //소켓에 메세지를 보냈을때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String senderEmail = getEmail(session);
        //모든 유저에게 보낸다 - 브로드 캐스팅
		for (WebSocketSession sess : sessions) {
			sess.sendMessage(new TextMessage( message.getPayload()));
		}

        System.out.println("메세지 받았음!!!");
        // 메세지가 어떤 형식으로 오느냐
        // "senderId,senderName, receiverId, receiverName, type, index"
        String msg = message.getPayload();
        System.out.println(msg);
        if(StringUtils.isNotEmpty(msg)) {
            String str = msg.substring(1, msg.length());
            str = msg.substring(0, msg.length()-1);
            String[] strs = msg.split(",");
            if(strs != null && strs.length == 5) {
                Long senderId = Long.parseLong(strs[0]);
                String senderName = strs[1];
                Long receiverId = Long.parseLong(strs[2]);
                String receiverName = strs[3];
                String type = strs[4];
                Long index = Long.parseLong(strs[5]);

                // 알림을 받아야 하는 사람이 로그인 해서 있다면
                // userSessionMap에서 그 값을 찾아봐야 함.
                WebSocketSession receiver = userSessionsMap.get(receiverId);

                // 알람이 챌린지에서부터 온 것이고 == 챌린지 좋아요 발생
                // 수신자가 현재 로그인한 상태라면
//                "<a type='external' href='/mentor/menteeboard/menteeboardView?seq="+seq+"&pg=1'>" + seq + "</a> 번 게시글에 댓글을 남겼습니다.
                if(type.equals("challenge") && receiver != null) {
                    // 알림 저장 - B
                    // 알림 전송 - B
                    // 알림의 내용물이 무엇이냐 ->  "senderId,senderName, receiverId, receiverName, 게시물 type, 게시물의 index"
                    // 메세지 처리 -> F
                    //
                    TextMessage tmpMsg = new TextMessage(senderName + "님이 챌린지에 좋아요를 눌렀습니다.");
                    receiver.sendMessage(tmpMsg);

                }else if(type.equals("post") && receiver != null) {
                    TextMessage tmpMsg = new TextMessage(senderName + "님이 포스트에 좋아요를 눌렀습니다.");
                    receiver.sendMessage(tmpMsg);
//                    tmpMsg.
                }
            }
            // 모임 신청 했을때
//            if(strs != null && strs.length == 5) {
//                String cmd = strs[0];
//                String mentee_name = strs[1];
//                String mentor_email = strs[2];
//                String meetingboard_seq = strs[3];
//                String participation_seq = strs[4];

                // 모임 작성한 멘토가 로그인 해있으면
//                WebSocketSession mentorSession = userSessionsMap.get(mentor_email);
//                if(cmd.equals("apply") && mentorSession != null) {
//                    TextMessage tmpMsg = new TextMessage(
//                            mentee_name + "님이 모임을 신청했습니다. " +"<a type='external' href='/mentor/participation/participationView?mseq="+ meetingboard_seq +"&pseq="+ participation_seq +"'>신청서 보기</a>");
//                    mentorSession.sendMessage(tmpMsg);
//                }
//            }
        }
    }

    //연결 해제될때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //System.out.println("afterConnectionClosed " + session + ", " + status);
        userSessionsMap.remove(session.getId());
        sessions.remove(session);
    }

    //웹소켓 email 가져오기
    private Long getId(WebSocketSession session) {
        Map<String, Object> httpSession = session.getAttributes();
        Long loginUser = (Long)httpSession.get("memberId");

        if(loginUser == null) {
            return -1L;
        } else {
            return loginUser;
        }
    }
}