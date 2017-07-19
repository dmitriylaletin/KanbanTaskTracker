/**
 * Created by Dmitriy Laletin on 20.07.17.
 */
function toggle(id) {
    var element = document.getElementById(id);
    if (element.style.display == 'block') {
        element.style.display = 'none';
    } else {
        element.style.display = 'block'
    }
}
