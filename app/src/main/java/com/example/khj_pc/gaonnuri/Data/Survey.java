package com.example.khj_pc.gaonnuri.Data;

public class Survey {
    private String title;

    public Survey() {
    }

    public Survey(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class SurveyMultiple extends Survey {

        private String content[] = new String[4];

        public SurveyMultiple() {
        }

        public SurveyMultiple(String title) {
            super(title);
        }

        public SurveyMultiple(String title, String[] content) {
            super(title);
            this.content = content;
        }

        public String[] getContent() {
            return content;
        }

        public void setContent(String[] content) {
            this.content = content;
        }
    }

    public static class SurveyOX extends Survey {

        private boolean content;

        public SurveyOX() {
        }

        public SurveyOX(String title) {
            super(title);
        }

        public SurveyOX(String title, boolean content) {
            super(title);
            this.content = content;
        }

        public boolean isContent() {
            return content;
        }

        public void setContent(boolean content) {
            this.content = content;
        }
    }

    public static class SurveyText extends Survey {

        private String content;

        public SurveyText() {
        }

        public SurveyText(String title) {
            super(title);
        }

        public SurveyText(String title, String content) {
            super(title);
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}

