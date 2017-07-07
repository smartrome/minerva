<img src="https://github.com/smartrome/minerva/blob/master/logo-png.png" height="94" height="71">

<h1 align="middle"><a id="user-content-templates" class="anchor" href="#Objectives" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Welcome To Minerva</h2>
<b>An interactive Android-based solution
for enriching the cultural experience in the Eternal City.</b>
<h3><a id="user-content-table-of-contents" class="anchor" href="#table-of-contents" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Table of Contents</h3>
<table>
<tbody>
<tr>
<td>
<ul>
<li><a href="#objectives">Objectives</a></li>
<li><a href="#good%20to%20know">Good to Know</a>
<ul>
<li><a href="#architecture">Architecture</a></li>
<li><a href="#design">Design</a></li>
<li><a href="#communication with services">Communication with services</a></li>
<li><a href="#roud map">Roud Map</a></li>
<li><a href="#test videos">Test Videos</a></li>
</ul>
</li>
<li><a href="#why minerva?">Why the name of Minerva?</a></li>
<li><a href="#slides">Slides</a></li>
<li><a href="#tests videos">Tests Videos</a></li>
<li><a href="#report">Report</a></li>
<li><a href="#contributions">Contributions</a></li>
</td>
<td width="100%">
<img src="https://github.com/smartrome/minerva/blob/master/Images/process.jpg" alt="responsive" height="50%" height="%50%" style="max-width:100%">
</td>
</tbody>
</table>
</ul>

<h2><a class="anchor" href="#objectives" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Objectives</h2>

Development of an Android application which takes the camera mirror image, and retrieves from the cloud interactive information about the place it's aiming. Currently the landmark recognition feature provided by the Google Cloud Vision API. Click <a href="https://cloud.google.com/vision/?gclid=CL2PjYatitMCFUORGwodg-kJpQ">here</a> to know more about this technology offered by Google. The Minerva application has been thought for being used in Rome, but the application offers the landmark recognition service world-wide.

<h2><a class="anchor" href="#good%20to%20know" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Good to know</h2>

<h3><a id="user-content-table-of-contents" class="anchor" href="#architecture" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Architecture</h3>

<table>
<thead>
<tr>
<th>Classification</th>
<th>Explanation</th>
</tr>
</thead>
<tbody>

<tr>
<td><a href="https://cloud.google.com/?utm_source=google&utm_medium=cpc&utm_campaign=2017-q1-cloud-emea-gcp-bkws-freetrial&gclid=Cj0KEQjwv_fKBRCG8a3ao-OQuZ8BEiQAvpHp6MPpBnrZg-kPmedyPw4o_k5WZXgR5C6CugyOm-nYLH8aAql38P8HAQ&dclid=CLDr48vU9dQCFeehUQodR7UHuQ">Server Side</a></td>
<td width="100%"  align="middle" >
<a href="" target="_blank"><img src="https://github.com/smartrome/minerva/blob/master/Images/Google_Cloud_Platform.png" alt="responsive" height="128" height="112" style="max-width:100%"></a>
<p>We used Google Cloud Platform as the backbone of Minerva</p>
</td>
</tr>
<tr>
<td><a href="">API</a></td>
<td width="100%"  align="middle" >
<a href="" target="_blank"><img src="https://github.com/smartrome/minerva/blob/master/Images/googlemap.png" alt="responsive" height="128" height="112" style="max-width:100%;"></a>
<p>We used Google Map API as our location Service</p>
</td></tr>
<tr>
<td><a href="">API</a></td>
<td width="100%" align="middle" >
<a href="" target="_blank"><img src="https://github.com/smartrome/minerva/blob/master/Images/Wikipedia_Logo_1.0.png" alt="responsive" height="128" height="112" style="max-width:100%;"></a>
<p>We used Wikipeda API to gather Information about the place</p>
</td></tr>
</tbody></table>
<h3><a id="user-content-admin-dashboards" class="anchor" href="#design" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Design</h3>
<table>
<thead>
<tr>
<th>Page</th>
<th>Screenshot</th>
</tr>
</thead>
<tbody>
<tr>
<td><p>Main Page</p></td>
<td align="middle"><img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-06-23_11-39-52.jpg" width="240" height="426" alt="responsive">
<img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-07-07_00-22-07.jpg" width="240" height="426" alt="responsive"></td>
</tr>

<tr>
<td><p>Gallery</p></td>
<td align="middle"><img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-06-23_11-39-47.jpg" width="240" height="426" alt="responsive"></td>
</tr>

<tr>
<td ><p>Camera</p></td>
<td align="middle"><img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-06-23_11-39-40.jpg" width="240" height="426" alt="responsive"></td>
</tr>

<tr>
<td ><p>Response</p></td>
<td align="middle"><img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-07-06_12-12-30.jpg" width="240" height="426" alt="responsive"></td>
</tr>

<tr>
<td ><p>Wiki page</p></td>
<td align="middle"><img src="https://github.com/smartrome/minerva/blob/master/Images/photo_2017-06-23_11-39-22.jpg" width="240" height="426" alt="responsive"></td>
</tr>
</tbody></table>

<h3>Communication with Services</h3>

<img src="https://github.com/smartrome/minerva/blob/master/Images/architecture.png">

In the left side of the picture we have the Minerva solution which is installed in an Android device. In its first version the
application has been developed to work on Android platforms that goes from the Android API Level 17 (version
4.2) up to the API Level 25 (version 7.1). In addition to do that it is completely assumed the provision of a
camera on the device. The application runs according the activity flow sequence shown in the previous section.
Whenever the application selects an image (either from the camera through the CameraActivity or from the
device image gallery through the GalleryActivity) it is sent a HTTP POST request message to the Google Cloud
Vision API. The Google Cloud Vision API address is https://vision.googleapis.com/v1/images:annotate?
key=appKey where appKey refers to the API that is given to the application having registered it within the Cloud
Vision API internal system. The request message also transports a JSON message within its payload in which is
inserted the landmark image compressed as well as an indication about which feature of the Vision API we want
to use which in the application case is landmark recognition.

Having received the landmark name plus its coordinates, this is internally saved in the application state, and then
it is presented to the user the ResultActivity interface. Within this module it is
called the service of the Wikipedia API. For this case the request is a simple
HTTP GET message in which the request attributes are put in the URL query string. The following string is a
call example towards the Wikipedia API.

https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=Colosseum&redirects=1

The basic address is of the API is https://en.wikipedia.org/w/api.php. The rest of the string are the parameters
used for the call. The format parameter explains how we expect to receive the data, in this case we set JSON.
The action and prop attributes indicate that this call wants to query article extracts. The motivation of this
specification is because the Wikipedia API is more than query articles. The Wikipedia API provides other
services such as modify articles meta-data or even modify an article. The exintro parameter specify that we are
only interested just in the extract introduction instead of the whole article. The explaintext parameter indicates to
the service that we want to receive the article extract in plain-text format (e.g. without HTML or other special
tags).

The titles parameter indicates which is the article extract that we are looking for (e.g. the extract about the
Colosseum). Finally, the redirects parameter will redirect the search to the article extract with more similarity on
the name. This last parameter is very useful since we are working with data and names that come from different
systems (Google and Wikipedia respectively). For instance, if the Google Cloud Vision API gives as a response
“Trevi Fountain”, and in other hand the article name about the Trevi Fountain is called instead “The Fountain of
Trevi” the redirects parameter will achieve the match between these two strings thereby assuring a correct
response.The response of this service call to the Wikipedia API will be a JSON object that will embedded the article in
one of its attributes. The parsing is handling by the application in order to show to the user the information about
the landmark.

<h3><a id="user-content-retro-themes" class="anchor" href="#road map" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Road Map</h3>
<table>
<thead>
<tr>
<th>Phase</th>
<th>Chart</th>
</tr>
</thead>
<tbody>
<tr>
<td ><p>ALL</p></td>
<td align="middle" width="100%"><img src="https://github.com/smartrome/minerva/blob/master/Images/road.png" height="70%" height="70%" alt="responsive" style="max-width:100%;"></td>
</tr>
</tbody></table>


<h2><a id="user-content-templates" class="anchor" href="#why minerva?" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Why the name of Minerva?</h2>
<table>
<tbody>
<tr>
<td>
Minerva was the Roman goddess of wisdom, knowledge & the sponsor of arts. Through her name we want to transmit to the user the feeling that our solution in development is the right choice for letting him/her know quickly about whichever cultural spot that he/she might find in Rome.
</td>
<td align="middle">
 <a href="https://youtu.be/ESH9aoOsrTY"><img src="https://github.com/smartrome/minerva/blob/master/Images/ISST-Videos.jpg" eight="45" width="45" alt="responsive" style="max-width:100%;"></p>See the application video on YouTube</a>
</td>
</tr>
</tbody>
</table>

<h2><a id="user-content-templates" class="anchor" href="#slides" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Slides</h2>
<table>
<thead>
<tr>
<td>
    Series
</td>
<td align="middle">
</td>
</tr>
</thead>
<tbody>
<tr>
 <td>
 First
 </td>
<td >
 <a href="http://www.slideshare.net/juliocesarcarrasquel/team-presentation-and-project-proposal"><img src="https://github.com/smartrome/minerva/blob/master/Images/pp.jpg" width="60" height="39" alt="responsive"> Team presentation and project proposal</a>
</td>
</tr>

<tr>
 <td>
 Second
 </td>
<td >
 <a href="https://www.slideshare.net/RaziehAkbari/minerva-second-presentation"><img src="https://github.com/smartrome/minerva/blob/master/Images/pp.jpg" width="60" height="39" alt="responsive">  User Experience and System Design </a>
</td>
</tr>

<tr>
 <td>
 Second
 </td>
<td >
 <a href="https://www.slideshare.net/RaziehAkbari/minerva-75560233"><img src="https://github.com/smartrome/minerva/blob/master/Images/pp.jpg" width="60" height="39" alt="responsive">   Introductory slides used for the presentation of our MVP </a>
</td>
</tr>

<tr>
 <td>
 Second
 </td>
<td >
 <a href="https://www.slideshare.net/RaziehAkbari/minerva-exam-presentation"><img src="https://github.com/smartrome/minerva/blob/master/Images/pp.jpg" width="60" height="39" alt="responsive"> Final presentation </a>
</td>
</tr>
</tbody>
</table>


<h2><a id="user-content-templates" class="anchor" href="#tests videos" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Tests Videos</h2>
<table>
<thead>
<tr>
<td><b>Video</b></td>
<td></td>
</tr>
</thead>
<tbody>
<tr><td>Camera Test</td>
<td><a href="https://youtu.be/So76zOb8fTU"><img src="https://github.com/smartrome/minerva/blob/master/Images/ISST-Videos.jpg" height="40" width="40" alt="responsive" style="max-width:100%;"> A video on YouTube testing using an image of the device's Camera </a></td>
</tr>
</tbody>
<tr><td>Gallery Test</td>
<td><a href="https://youtu.be/WdBYzg6CeVg"><img src="https://github.com/smartrome/minerva/blob/master/Images/ISST-Videos.jpg" height="40" width="40" alt="responsive" style="max-width:100%;"> A video on YouTube testing using an image of the device's gallery </a></td>
</tr>
</tbody>
</table>

<h2><a id="user-content-templates" class="anchor" href="#report" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Report</h2>
 <p>- <a href="https://www.slideshare.net/juliocesarcarrasquel/minerva-solution-technical-report"> Technical Report </a> - A paper presenting a technical review of the application as well as other general considerations</p>

<h2><a class="anchor" href="#contributions" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>Contributions</h2>

- <b>Julio César Carrasquel</b>
  - <img src="https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAANyAAAAJGRlZTNlZDQwLTk4YTItNDA1MS04MzBjLWJmNGQ5M2RmZGUxYw.png" height="20" width="20"> <b>Linkedin</b>:  <a href="https://www.linkedin.com/in/julio-c%C3%A9sar-carrasquel-b5729844/">https://www.linkedin.com/in/julio-césar-carrasquel-b5729844/</a> 
  - <img id="imageLogo" class="logo-image" src="http://www.freeiconspng.com/uploads/iconmonstr-email-4-icon-27.png" alt="iconmonstr email 4 icon" style="border-width:0px;" height="20" width="20"> <b>Email</b>: carrasquelgamez.1726154@studenti.uniroma1.it
- <b>Razieh Akbari</b>
  -  <img src="https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAANyAAAAJGRlZTNlZDQwLTk4YTItNDA1MS04MzBjLWJmNGQ5M2RmZGUxYw.png" height="20" width="20"> <b>Linkedin</b>:  <a href="https://www.linkedin.com/in/razieh-akbari-2019b075/"> https://www.linkedin.com/in/razieh-akbari-2019b075/</a>
  - <img id="imageLogo" class="logo-image" src="http://www.freeiconspng.com/uploads/iconmonstr-email-4-icon-27.png" alt="iconmonstr email 4 icon" style="border-width:0px;" height="20" width="20"> <b>Email</b>: raziehakbari409@gmail.com
  


