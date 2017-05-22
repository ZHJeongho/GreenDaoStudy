package com.jeongho.greendaostudy.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Jeongho on 2017/5/11.
 */
@Entity
public class DotaHero {
    @Id
    private long id;

    private String name;

    private int hp;

    private int mp;

    private int exp;

    @Generated(hash = 623523123)
    public DotaHero(long id, String name, int hp, int mp, int exp) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.exp = exp;
    }

    @Generated(hash = 500107644)
    public DotaHero() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
