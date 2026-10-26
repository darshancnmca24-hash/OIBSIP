const bookingForm = document.getElementById("bookingForm");
const bookingMessage = document.getElementById("bookingMessage");

bookingForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(bookingForm);

    const reservation = {
        passengerName: formData.get("passengerName").trim(),
        trainNumber: formData.get("trainNumber").trim(),
        trainName: formData.get("trainName").trim(),
        travelDate: formData.get("travelDate"),
        source: formData.get("source").trim(),
        destination: formData.get("destination").trim(),
        travelClass: formData.get("travelClass")
    };

    if (reservation.source.toLowerCase() === reservation.destination.toLowerCase()) {
        bookingMessage.textContent =
            "Source and destination stations must be different.";
        return;
    }

    bookingMessage.textContent = "Connecting to the reservation server...";

    try {
        const response = await fetch("/api/reservations", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reservation)
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || "Unable to book reservation.");
        }

        bookingMessage.textContent =
            `Reservation booked successfully! Your PNR is: ${result.pnr}`;

        bookingForm.reset();
    } catch (error) {
        bookingMessage.textContent =
            error.message || "Could not connect to the reservation server.";
    }
});

// PNR search functionality
const searchForm = document.getElementById("searchForm");
const searchMessage = document.getElementById("searchMessage");
const reservationDetails = document.getElementById("reservationDetails");

searchForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const pnr = document.getElementById("searchPnr").value.trim().toUpperCase();

    searchMessage.textContent = "Searching for your reservation...";
    reservationDetails.replaceChildren();

    try {
        const response = await fetch(
            `/api/reservations/${encodeURIComponent(pnr)}`
        );

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || "Reservation not found.");
        }

        searchMessage.textContent = "Reservation found successfully!";

        const fields = [
            ["PNR", result.pnr],
            ["Passenger Name", result.passengerName],
            ["Train Number", result.trainNumber],
            ["Train Name", result.trainName],
            ["Travel Date", result.travelDate],
            ["From", result.source],
            ["To", result.destination],
            ["Travel Class", result.travelClass]
        ];

        const details = document.createElement("div");
        details.className = "reservation-details";

        fields.forEach(([label, value]) => {
            const row = document.createElement("p");
            const strong = document.createElement("strong");
            strong.textContent = `${label}: `;
            row.append(strong, document.createTextNode(value ?? ""));
            details.appendChild(row);
        });

        reservationDetails.appendChild(details);
    } catch (error) {
        searchMessage.textContent =
            error.message || "Could not connect to the reservation server.";
    }
});