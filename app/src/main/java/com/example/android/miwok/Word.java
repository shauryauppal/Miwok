package com.example.android.miwok;

/**
 * Created by shaurya on 29/6/17.
 */

public class Word {


    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mMiwokImage=-1;

    public Word(String DefaultTranslation,String MiwokTranslation)
    {
        mMiwokTranslation=MiwokTranslation;
        mDefaultTranslation=DefaultTranslation;
    }

    /**
     *
     * @param DefaultTranslation
     * @return english translation
     * @param MiwokTranslation
     * @return Miwok translation
     * @param MiwokImage
     * @return Miwok iamge
     */
    public Word(String DefaultTranslation,String MiwokTranslation,int MiwokImage)
    {
        mMiwokTranslation=MiwokTranslation;
        mDefaultTranslation=DefaultTranslation;
        mMiwokImage=MiwokImage;
    }
    /**
     * this will return english translation
     * @return english translation
     */
    public String getdefaultTranslation()
    {
        return mDefaultTranslation;
    }

    /**
     *
     * @return Miwok translation
     */

    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    public int getImage() {return mMiwokImage;}

    public boolean hasImage()
    {
        if(mMiwokImage==-1)
            return false;
        else return true;
    }
}
