# About

This is a simple, minimal news application for online magazines and newspapers.

Our News app consumes all of the data it needs from this **RESTful API**: https://github.com/fknussel/news-backend

# Architectural Overview

This is a high-level design diagram of the application:

![Architectural Overview](/docs/architecture.png)

# Branches

The following is a list of all of the features we'll to introduce in the app.

### Feature #1: Timeline and Details Views

> Branch codename: `FEAT-1`

So far the application is somewhat limited in functionality, the user can just browse all of the news in the news feed, and see an extended version for each post when clicking on it.

**Side note:** in order to test basic functionality, data sources have been mocked using the getDummyPosts() method implemented in the Post.java model class.

This branch focuses mainly on introducing appearance changes on both main activity (news feed) and detail activity.

- [x] Navigation drawer with categories
- [x] ActionBar menu options
- [x] Material design color scheme
- [x] Timeline layout
- [x] ImageView with rounded corners on news feed

Here's the link to the pull request for part 1: https://github.com/fknussel/News/pull/1

### Feature #2: Networking and Navigation Drawer

> Branch Codename: `FEAT-2`
> Pull Request: https://github.com/fknussel/News/pull/2

This branch introduces networking capabilities using **Retrofit**, allowing the app to fetch the latest posts from the RESTful API.

- [x] Navigation drawer with categories

# Dependencies

This application uses the following libraries:

* [**Picasso**](http://square.github.io/picasso/) for remote image loading and manipulation (first added on FEAT-1)
* [**Retrofit**](http://square.github.io/retrofit/) as a way of interfacing our RESTful API (first added on FEAT-2)
* [**GSON**](https://code.google.com/p/google-gson/) to convert Java Objects into their JSON representation, as well as to convert JSON strings to their equivalent Java objects (first added on FEAT-2)

### Talking to the Backend

This app is tightly-knit to the RESTful API it gets its data from. The model classes (`Post.java`, `Media.java` and so on) were built upon the API's interfaces. Go check the [web service source code](https://github.com/fknussel/news-backend) to get an insight of how this all works together.