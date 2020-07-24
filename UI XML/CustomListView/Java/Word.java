package e.vatsalkesarwani.miwok;

public class Word {          //custom class


        private String mDefaultTranslation;

        private String mMiwokTranslation;

        private int mMiwokResources;

        public Word(String defaultTranslation, String miwokTranslation) {
            mDefaultTranslation = defaultTranslation;
            mMiwokTranslation = miwokTranslation;
        }
        public Word(String defaultTranslation, String miwokTranslation,int  miwokResources)
        {
            mDefaultTranslation = defaultTranslation;
            mMiwokTranslation = miwokTranslation;
            mMiwokResources= miwokResources;
        }

        public String getDefaultTranslation() {
            return mDefaultTranslation;
        }

        public String getMiwokTranslation() {
            return mMiwokTranslation;
        }

        public int getResourcesId() { return mMiwokResources; }

}
