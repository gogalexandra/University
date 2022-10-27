let currentDroppable = null;

circle.onmousedown = function(event) {

    let shiftX = event.clientX - circle.getBoundingClientRect().left;
    let shiftY = event.clientY - circle.getBoundingClientRect().top;

    circle.style.position = 'absolute';
    circle.style.zIndex = 1000;
    document.body.append(circle);

    moveAt(event.pageX, event.pageY);

    function moveAt(pageX, pageY) {
        circle.style.left = pageX - shiftX + 'px';
        circle.style.top = pageY - shiftY + 'px';
    }

    function onMouseMove(event) {
        moveAt(event.pageX, event.pageY);

        circle.hidden = true;
        let elemBelow = document.elementFromPoint(event.clientX, event.clientY);
        circle.hidden = false;

        if (!elemBelow) return;

        let droppableBelow = elemBelow.closest('.droppable');
        if (currentDroppable !== droppableBelow) {
            if (currentDroppable) {
                leaveDroppable(currentDroppable);
            }
            currentDroppable = droppableBelow;
            if (currentDroppable) {
                enterDroppable(currentDroppable);
            }
        }
    }

    document.addEventListener('mousemove', onMouseMove);

    circle.onmouseup = function() {
        document.removeEventListener('mousemove', onMouseMove);
        circle.onmouseup = null;
    };

};

function enterDroppable(elem) {
    elem.style.background = 'pink';
}

function leaveDroppable(elem) {
    elem.style.background = '';
}

// circle.ondragstart = function() {
//     return false;
// };