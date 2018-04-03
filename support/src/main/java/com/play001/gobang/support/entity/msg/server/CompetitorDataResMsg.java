package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Player;

/**
 * 对手信息
 */
@MsgAnnotation(msgType = ServerMsgType.COMPETITOR_DATA_RES)
public class CompetitorDataResMsg extends ServerBaseMsg{

    private Player competitor;

    public CompetitorDataResMsg(long time) {
        super(ServerMsgType.COMPETITOR_DATA_RES, time);
    }

    public Player getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Player competitor) {
        this.competitor = competitor;
    }
}
