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

In this branch the following dependencies were added:

* Square's Picasso

Here's the link to the pull request for part 1: https://github.com/fknussel/News/pull/1

# Dependencies

This application uses the following libraries:

* **Picasso** for remote image loading and manipulation: http://square.github.io/picasso/
* **Retrofit** as a way of interfacing our RESTful API: http://square.github.io/retrofit/