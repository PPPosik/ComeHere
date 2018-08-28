package com.example.khj_pc.gaonnuri.Data;

public class SurveyResult {
    private String title;

    public SurveyResult() {
    }

    public SurveyResult(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Text extends SurveyResult {
        private String content;

        public Text(String content) {
            this.content = content;
        }

        public Text(String title, String content) {
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

    public static class OX extends SurveyResult {
        private int[] percent;

        public OX(int[] percent) {
            this.percent = percent;
        }

        public OX(String title, int[] percent) {
            super(title);
            this.percent = percent;
        }

        public int[] getPercent() {
            return percent;
        }

        public void setPercent(int[] percent) {
            this.percent = percent;
        }
    }

    public static class Multiple extends SurveyResult {
        private String[] content;
        private int[] percent;

        public Multiple(String[] content) {
            this.content = content;
        }

        public Multiple(String title, String[] content) {
            super(title);
            this.content = content;
        }

        public Multiple(String[] content, int[] percent) {
            this.content = content;
            this.percent = percent;
        }

        public Multiple(String title, String[] content, int[] percent) {
            super(title);
            this.content = content;
            this.percent = percent;
        }

        public String[] getContent() {
            return content;
        }

        public void setContent(String[] content) {
            this.content = content;
        }

        public int[] getPercent() {
            return percent;
        }

        public void setPercent(int[] percent) {
            this.percent = percent;
        }
    }
}
