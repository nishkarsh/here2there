# here2there [![Build Status](https://travis-ci.org/nishkarsh/here2there.svg?branch=master)](https://travis-ci.org/nishkarsh/here2there)
### This is a sample transit app with no intention to develop the app for real use.

#### Here are a few points to consider:
* The app in total has 3 screens:
  - Route search screen: This screen provides text boxes to specify source and the destination stops. On click of search icon, a network call is made and the route list and provider information is fetched. The route list is then shown on the UI. Clicking on the list item takes the user to next screen (i.e. Route Details). Provider names are clickable and on clicking of which a dialog is shown that has provider information. The text boxes are dummy as of now and no routing logic has been implemented. This means that no matter what value is specified in the boxes, the route list presented would remain exact same.
  - Route details screen: This screen is shown when user taps on any route. It contains all the segments included in the route and the polylines are displayed on the map. Clicking on the segments zooms in to the selected segment.
  - Provider information screen: This is the same as the dialog fragment that is presenter on clicking of provider name on search results.

* The app is designed using MVP (Model View Presenter) pattern. The philosophy kept in mind is to keep the view as dumb as possible and extract out all the business logic into presenters. These presenters are completely unit tested (are even drived through tests). This is to make the tests run faster as no android context is included in the tested code. Views are then avoided being tested as absence of logic makes it less prone to bugs. At some places the logic might still be in the view, which in future commits would have been extracted out into a ViewModel. This would be a classic example of using MVP along with MVVM.

* Libraries used in the app:
  - There are many popular android libraries used for different purposes: Retrofit for networking, Jackson for serializing/deserializing the request/response, Butterknife for view injection, Google maps, Parceler, JodaTime android, etc.
  - There are use cases which could be handled using some other popular libraries, for instance, the data being fetched, if does not changes frequently, could be synced at regular intervals using GCM tasks. For the same purpose, it makes sense to store the data locally into a DB, Realm would be a good choice for that. Images are not being displayed as of now, since the images are SVGs, picasso doesn't support it yet. Glide along with androidSvg could be used to display SVGs.
  
* Architecure of app:
  The architecture is very seriously kept in mind while developing the app so that it remains extensible for more features to accomodate or to keep even the future changes to the same features effortless and simple.
