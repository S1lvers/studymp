package test.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by qwerty on 05.04.2017.
 */
@XmlRootElement(name = "trans")
@XmlType(propOrder = {"tranid", "pursesrc", "pursedest", "amount", "period", "pcode", "desc", "wminvid", "onlyauth"})
@JsonPropertyOrder({"courseName", "score"})
public class Trans implements Serializable{

    /**
     * номер перевода
     * номер перевода в системе учета отправителя; любое целое число без знака(целое число > 0; максимально 2^31 -1),
     * должно быть уникальным в пределах WMID, который подписывает запрос.
     * Два перевода с одним и тем же tranid с одного WMID (даже с разных кошельков) осуществить невозможно.
     * Уникальность значения tranid контролируется в интервале не менее одного года.
     */
    private int tranid;

    /**
     * номер кошелька с которого выполняется перевод (отправитель)
     */
    private String pursesrc;

    /**
     * номер кошелька, на который выполняется перевод (получатель)
     */
    private String pursedest;

    /**
     * переводимая сумма
     * число с плавающей точкой (разделитель - .(точка) )
     * и для десяти с половиной может выглядеть так: 10.5;
     * незначащие нули в конце и точка, если число целое, должны отсутствовать,
     * например, 10.50 - не верно, 10.5 - верно, 9. - не верно, 9 - верно)
     */
    private double amount;

    /**
     * срок протекции сделки в днях
     * целое число от 0 до 120; 0 - без протекции
     */
    private int period;

    /**
     * код протекции сделки
     * произвольная строка от 5 до 255 символов;
     * пробелы в начале или конце не допускаются
     */
    private String pcode;

    /**
     * описание оплачиваемого товара или услуги
     * произвольная строка от 0 до 255 символов;
     * пробелы в начале или конце не допускаются, в кодировке Win1251
     */
    private String desc;

    /**
     * номер счета (в системе WebMoney), по которому выполняется перевод
     * целое число > 0; если 0 - перевод не по счету; максимально 2^31 -1
     */
    private long wminvid;

    /**
     * учитывать разрешение получателя
     * обязательный! 1 - перевод будет выполняться только если получатель разрешает перевод
     * (в противном случае код возврата - 35). Получатель может запретить переводить себе средства в двух случаях.
     * В первом, если отправитель является корреспондентом получателя и получатель явно в свойствах корреспондента
     * (раздел ограничения) запретил ему совершать в свою пользу транзакции и во-втором,
     * если отправитель не является корреспондентом и получатель запретил всем неавторизованным
     * (не являющимся его корреспондентами) совершать в свою пользу транзакции.
     */
    private int onlyauth;

    public @XmlElement int getTranid() {
        return tranid;
    }

    public @XmlElement String getPursesrc() {
        return pursesrc;
    }

    public @XmlElement String getPursedest() {
        return pursedest;
    }

    public @XmlElement double getAmount() {
        return amount;
    }

    public @XmlElement int getPeriod() {
        return period;
    }

    public @XmlElement String getPcode() {
        return pcode;
    }

    public @XmlElement String getDesc() {
        return desc;
    }

    public @XmlElement long getWminvid() {
        return wminvid;
    }

    public @XmlElement int getOnlyauth() {
        return onlyauth;
    }

    public void setTranid(int tranid) {
        this.tranid = tranid;
    }

    public void setPursesrc(String pursesrc) {
        this.pursesrc = pursesrc;
    }

    public void setPursedest(String pursedest) {
        this.pursedest = pursedest;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setWminvid(long wminvid) {
        this.wminvid = wminvid;
    }

    public void setOnlyauth(int onlyauth) {
        this.onlyauth = onlyauth;
    }

    /**
     *
     * @param tranid - номер перевода
     * @param pursesrc - номер кошелька с которого выполняется перевод (отправитель)
     * @param pursedest - номер кошелька, на который выполняется перевод (получатель)
     * @param amount - переводимая сумма
     * @param period - срок протекции сделки в днях
     * @param pcode - код протекции сделки
     * @param desc - описание оплачиваемого товара или услуги
     * @param wminvid - номер счета (в системе WebMoney), по которому выполняется перевод
     * @param onlyauth - учитывать разрешение получателя
     */
    public Trans(int tranid, String pursesrc, String pursedest, double amount, int period, String pcode, String desc, long wminvid, int onlyauth) {
        this.tranid = tranid;
        this.pursesrc = pursesrc;
        this.pursedest = pursedest;
        this.amount = amount;
        this.period = period;
        this.pcode = pcode;
        this.desc = desc;
        this.wminvid = wminvid;
        this.onlyauth = onlyauth;
    }

    public Trans(){

    }
}
