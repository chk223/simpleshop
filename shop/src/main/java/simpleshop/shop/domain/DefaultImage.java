package simpleshop.shop.domain;

public enum DefaultImage {
    ITEM("https://search.pstatic.net/sunny/?src=https%3A%2F%2Fyt3.googleusercontent.com%2FfeejPiIhLm9gIRag9H1zuIbn4l0oujYNwc8-Ivl2q9dWGTajAUIqwBZINoVd2QJti4CHWbwWYdg%3Ds900-c-k-c0x00ffffff-no-rj&type=a340");

    private final String url;

    DefaultImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
