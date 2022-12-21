package com.example.pokeapifinal.models;

public class Pokemon {
    private String name;
    private String url;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumber()
    {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
