package com.ewmw.addr.services.fts.manticore.response.docs;

public class HitResult {
    private String id;
    private int score;
    private AddrSource source;

    public HitResult(String id, int score, AddrSource source) {
        this.id = id;
        this.score = score;
        this.source = source;
    }

    public HitResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public AddrSource getSource() {
        return source;
    }

    public void setSource(AddrSource source) {
        this.source = source;
    }
}
