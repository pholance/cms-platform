package com.yidumen.cms.entity;

import com.yidumen.cms.MessageType;
import com.yidumen.cms.RecordType;

import java.io.Serializable;
import java.util.Objects;

/**
 * 公众号与订阅者之间的消息。
 *
 * @author 蔡迪旻
 */
public class Record implements Serializable {
    private Long id;
    private Fans owner;
    /**
     * 微信的发送的msgid号
     */
    private Message message;
    private String msgId;
    private boolean readed;
    private MessageType msgType;
    private RecordType recordType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fans getOwner() {
        return owner;
    }

    public void setOwner(Fans owner) {
        this.owner = owner;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.msgType);
        hash = 47 * hash + Objects.hashCode(this.message);
        hash = 47 * hash + (this.readed ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        if (this.msgId != other.msgId) {
            return false;
        }
        if (this.msgType != other.msgType) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.readed != other.readed) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", msgId=" + msgId + ", msgType=" + msgType + ", message=" + message + ", readed=" + readed + '}';
    }


}
