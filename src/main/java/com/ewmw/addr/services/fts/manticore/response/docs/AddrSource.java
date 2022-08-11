package com.ewmw.addr.services.fts.manticore.response.docs;

public class AddrSource {
    private String addr;
    private String path;

    public AddrSource(String addr, String path) {
        this.addr = addr;
        this.path = path;
    }

    public AddrSource() {
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
