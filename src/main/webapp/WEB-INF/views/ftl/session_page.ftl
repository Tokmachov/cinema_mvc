<html>
<header>
   <style>
      body {
         display: flex;
         flex-direction: column;
         align-items: center;
         background-color: green;
      }
      img {
         max-width: 60%;
         height: auto;
      }
      #movieDescription {
         word-break: break-all;
      }
   </style>
   <title>${sessionPageTitleKey}</title>
</header>
<body>
   <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
   <div>Hall title: ${sessionModelKey.movieHallTitle}</div>
   <div>Number of seats: ${sessionModelKey.numberOfSeats}</div>
   <div>Movie title: ${sessionModelKey.movieTitle}</div>
   <div>Year: ${sessionModelKey.yearCreated}</div>
   <div>Age restriction: ${sessionModelKey.ageRestriction}</div>
   <img src="${moviePosterBaseUrlKey}/${sessionModelKey.posterId}" alt="poster for movie ${sessionModelKey.movieTitle}">
   <div id="movieDescription">${sessionModelKey.movieDescription}</div>
</body>
</html>