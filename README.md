# About

This is a simple, minimal news application for online magazines and newspapers. Our News app consumes all of the data it needs from this [RESTful API](https://github.com/fknussel/news-backend).

# Architectural Overview

This is a high-level design diagram of the application:

![Architectural Overview](/docs/architecture.png)

# Branch Roadmap

### Feature #1: Timeline and Details Views

> Branch codename: `FEAT-1`<br />
> Link to Pull Request: https://github.com/fknussel/News/pull/1

So far the application is somewhat limited in functionality, the user can just browse all of the news in the news feed, and see an extended version for each post when clicking on it. This branch focuses mainly on introducing appearance changes on both main activity (news feed) and detail activity.

- [x] Basic Navigation drawer with categories
- [x] ActionBar menu options
- [x] Material design color scheme
- [x] Timeline layout
- [x] ImageView with rounded corners on news feed

**Side note:** in order to test basic functionality, data sources have been mocked using the `getDummyPosts()` method implemented in the `Post.java` model class.

### Feature #2: Networking and Navigation Drawer

> Branch Codename: `FEAT-2`<br />
> Link to Pull Request: https://github.com/fknussel/News/pull/2

This branch introduces networking capabilities using **Retrofit**, allowing the app to fetch the latest posts from the RESTful API.

- [x] Improved Navigation drawer with categories
- [x] Fetch latest posts from remote API
- [x] Model classes definition (`Post.java` and `Media.java`)
- [x] Display dates in a human-friendly format

### Feature #3: Data Caching

> Branch Codename: `FEAT-3`<br />
> Link to Pull Request: https://github.com/fknussel/News/pull/3

So far, whenever we want to display a particular post or list the latest articles, we needed to query the API. This is not cool whatsoever, unnecessary network calls should be avoided whenever possible. Instead we should cache the data into our local database immediately after we query the web service for the first time. This is what this branch focuses on, making the app more efficient in terms of network calls.

We also went ahead and made some UI tweaks to `DetailActivity`: the action bar is now transparent, the pictures kinda overlay the action bar but are still fully visible since it's transparent now, also posts' titles get displayed on top of a black-to-transparent gradient applied to the image (we are no longer using the semi-opaque black box underneath the title).

# Dependencies

This application uses the following libraries:

* [Picasso](http://square.github.io/picasso/) for remote image loading and manipulation (added on `FEAT-1`)
* [Retrofit](http://square.github.io/retrofit/) as a way of interfacing our RESTful API (added on `FEAT-2`)
* [GSON](https://code.google.com/p/google-gson/) to convert Java Objects into their JSON representation, as well as to convert JSON strings to their equivalent Java objects (added on `FEAT-2`)
* [DATE4J](http://www.date4j.net/) for date manipulation, as an alternative to `Date`, `Calendar` and related Java classes (added on `FEAT-2`)

### Talking to the Backend

This app is tightly-knit to the RESTful API it gets its data from. The model classes (`Post.java`, `Media.java` and so on) were built upon the API's interfaces. Go check the [web service source code](https://github.com/fknussel/news-backend) to get an insight of how this all works together.

The location of the RESTful API is hardcoded in this class:

```
public class ApiClient {
    private static final String API_URL = "http://api.fknussel.com";
```

**Heads up!** :hand: DO NOT include a trailing slash here.