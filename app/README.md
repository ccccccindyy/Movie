=== Architecture ===

==== UI Layer ====
- SearchFragment: Responsible for displaying a search interface and navigating to DetailFragment when a movie is selected, passing the movieId.
- DetailFragment: Displays detailed information about a specific movie, fetched using the movieId. It interacts with the MyViewModel to initiate data fetching and observe the results.

==== Service Layer ====
- MovieSearchService
- MovieDetailService
Only interact with their respective domain level objects (Movie and MovieSearchResponse) and handle the different type of request response (Success, Error 4XX and 5XX, or network failure)

==== Data Layer ====
- MovieApiDataSource: the base repository that handle API calls
- MovieSearchRepository and MovieDetailRepository, inherits from the former and request specific endpoints

