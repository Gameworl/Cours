package iutinfo.lp.devmob.devoirbdandroid.model;

public class CSEvent {
    int _id;
    String _cseventname;
    String _cseventdate;

    public CSEvent() {
    }

    public CSEvent(String _cseventname, String _cseventdate) {
        this._cseventname = _cseventname;
        this._cseventdate = _cseventdate;
    }

    public CSEvent(int _id, String _cseventname, String _cseventdate) {
        this._id = _id;
        this._cseventname = _cseventname;
        this._cseventdate = _cseventdate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_cseventname() {
        return _cseventname;
    }

    public void set_cseventname(String _cseventname) {
        this._cseventname = _cseventname;
    }

    public String get_cseventdate() {
        return _cseventdate;
    }

    public void set_cseventdate(String _cseventdate) {
        this._cseventdate = _cseventdate;
    }
}
