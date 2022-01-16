var FACTORIAL_URL = '/factorial/21000';
var TEMPERATURE_URL = '/temperature';

function generateWorkload() {
    callWithInterval(() => infiniteReload(FACTORIAL_URL), 300, 8);

    infiniteReload(TEMPERATURE_URL, 60000);

    var workloadStatusBar = document.getElementById('workload-status');
    workloadStatusBar.innerHTML = '<p>Workload generation has started. Reboot this page to stop it.</p>';
}

function callWithInterval(callback, interval, times) {
    if (times <= 0) {
        return;
    }

    setTimeout(callback);
    setTimeout(() => callWithInterval(callback, interval, times - 1), interval);
}

function infiniteReload(url, sleepMs) {
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        setTimeout(() => infiniteReload(url, sleepMs), sleepMs)
    }
    xhttp.open('GET', url);
    xhttp.send();
}
