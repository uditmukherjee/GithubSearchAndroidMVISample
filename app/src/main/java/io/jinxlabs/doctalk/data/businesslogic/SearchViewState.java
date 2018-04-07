package io.jinxlabs.doctalk.data.businesslogic;

import java.util.List;

import io.jinxlabs.doctalk.data.businesslogic.model.User;

public interface SearchViewState {
    final class SearchNotStartedYet implements SearchViewState {
    }

    final class Loading implements SearchViewState {
    }

    final class EmptyResult implements SearchViewState {
        private final String searchQueryText;

        public EmptyResult(String searchQueryText) {
            this.searchQueryText = searchQueryText;
        }

        public String getSearchQueryText() {
            return searchQueryText;
        }

        @Override public String toString() {
            return "EmptyResult{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    '}';
        }
    }

    final class SearchResult implements SearchViewState {
        private final String searchQueryText;
        private final List<User> result;

        public SearchResult(String searchQueryText, List<User> result) {
            this.searchQueryText = searchQueryText;
            this.result = result;
        }

        public String getSearchQueryText() {
            return searchQueryText;
        }

        public List<User> getResult() {
            return result;
        }

        @Override public String toString() {
            return "SearchResult{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

    final class Error implements SearchViewState {
        private final String searchQueryText;
        private final Throwable error;

        public Error(String searchQueryText, Throwable error) {
            this.searchQueryText = searchQueryText;
            this.error = error;
        }

        public String getSearchQueryText() {
            return searchQueryText;
        }

        public Throwable getError() {
            return error;
        }

        @Override public String toString() {
            return "Error{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    ", error=" + error +
                    '}';
        }
    }

}
