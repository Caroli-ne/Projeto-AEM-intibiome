import { Carousel } from "bootstrap";

(function () {
    "use strict";

    function initialize(root) {
        root.querySelectorAll(".cmp-minimal-carousel__image").forEach(function (image) {
            image.style.setProperty("--mobile-position", image.dataset.mobilePosition || "50% center");
        });

        const pauseButton = root.querySelector('[data-action="pause"]');
        if (!pauseButton) {
            return;
        }

        const carousel = Carousel.getOrCreateInstance(root);
        let paused = false;
        pauseButton.addEventListener("click", function () {
            paused = !paused;
            pauseButton.classList.toggle("is-paused", paused);
            pauseButton.setAttribute("aria-pressed", String(paused));
            pauseButton.setAttribute("aria-label", paused ? "Retomar carrossel" : "Pausar carrossel");
            if (paused) {
                carousel.pause();
            } else {
                carousel.cycle();
            }
        });
    }

    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll('[data-cmp-is="minimal-carousel"]').forEach(initialize);
    });
}());
