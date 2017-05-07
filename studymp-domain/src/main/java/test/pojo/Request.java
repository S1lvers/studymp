package test.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by qwerty on 05.04.2017.
 */
@XmlRootElement(name = "w3s.request")
@XmlType(propOrder = {"reqn", "wmid", "sign", "trans", "wmb_denomination"})
@JsonPropertyOrder({"reqn", "wmid", "sign", "trans", "wmb_denomination"})
public class Request implements Serializable{

    /**
     * целое без знака, макс. количество цифр - 15;
     * всегда должен быть больше номера предыдущего запроса на перевод средств!!!
     * Монотонность возрастания номера учитывается в привязке к WMID, подписывающему запрос.
     */
    private int reqn;

    /**
     * используется только при авторизации с ключами WM Keeper WinPro (Classic)
     */
    private String wmid;

    /**
     * формируется из параметров: reqn+tranid+pursesrc+pursedest+ amount+period+pcode+desc+wminvid
     * используется только при авторизации с ключами WM Keeper WinPro (Classic)
     */
    private String sign;

    /**
     *
     */
    private Trans trans;

    /**
     * обязательный! 1 - перевод будет выполняться только если отправитель передает данный параметр
     */
    private int wmb_denomination;


    public @XmlElement int getReqn() {
        return reqn;
    }

    public void setReqn(int reqn) {
        this.reqn = reqn;
    }

    public @XmlElement String getWmid() {
        return wmid;
    }

    public void setWmid(String wmid) {
        this.wmid = wmid;
    }

    public @XmlElement String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public @XmlElement Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }

    public @XmlElement int getWmb_denomination() {
        return wmb_denomination;
    }

    public void setWmb_denomination(int wmb_denomination) {
        this.wmb_denomination = wmb_denomination;
    }

    /**
     *
     * @param reqn - номер запроса
     * @param wmid - WMID подписавшего запрос
     * @param sign - подпись запроса
     * @param trans - параметры перевода
     * @param wmb_denomination - признак деноминации
     */
    public Request(int reqn, String wmid, String sign, Trans trans, int wmb_denomination) {
        this.reqn = reqn;
        this.wmid = wmid;
        this.sign = sign;
        this.trans = trans;
        this.wmb_denomination = wmb_denomination;
    }

    public Request(){

    }
}
