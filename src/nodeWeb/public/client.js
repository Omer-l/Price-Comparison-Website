//when page is loaded, run init
window.onload = init;

let search = "not changed";

var setSearch = new Vue({
    el: '#searchDiv',
    data: {
        searchInput: ""
    },
    methods: {
        search: function () {
            var localApp = this;
            localApp.searchInput = document.getElementById("searchInput").value;
            console.log("HERE THERE");
            console.log(localApp.searchInput);
        }
    }
})
