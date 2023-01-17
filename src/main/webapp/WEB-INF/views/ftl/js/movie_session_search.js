var inputElement = document.querySelector('input');
var searchResultsDivElement = document.querySelector('.searchResults');

inputElement.onkeyup = function() {
    searchResultsDivElement.innerHTML = '';
    if (this.value.length == 0) {
        return;
    }

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function() {
    if (this.readyState==XMLHttpRequest.DONE && this.status==200) {
        console.log('Received response')
    }
    
    xmlhttp.open("GET","?query="+str,true);
    xmlhttp.send();
        // var count = inputElement.value;
        // for (var i = 0; i < count; i++) {
        //     console.log("Element " + i);
        //     var movieSessionInfoElement = createMovieSessionInfoElement('poster ref', 'alien 2', 'green hall', '11.12.2022', '15:22');
        //     searchResultsDivElement.appendChild(movieSessionInfoElement);
        // }
    }
}
var createMovieSessionInfoElement = function(posterAbsRef, movieName, movieHallName, date, time) {
    var galleryElement = document.createElement('div');    
    galleryElement.className = 'gallery';

    var imgElement = document.createElement('img');
    imgElement.src = posterAbsRef;
    imgElement.alt = 'I\' blind!'

    var movieNameDivElement = document.createElement('div');    
    movieNameDivElement.innerHTML = movieName;

    var hallNameDivElement = document.createElement('div');
    hallNameDivElement.innerHTML = movieHallName;

    var dateDivElement = document.createElement('div');
    dateDivElement.innerHTML = date;

    var timeDivElement = document.createElement('div');
    timeDivElement.innerHTML = time;

    galleryElement.appendChild(imgElement);
    galleryElement.appendChild(movieNameDivElement);
    galleryElement.appendChild(hallNameDivElement);
    galleryElement.appendChild(dateDivElement);
    galleryElement.appendChild(timeDivElement);
    return galleryElement;
}