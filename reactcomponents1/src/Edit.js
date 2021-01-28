import { useState, useRef } from "react";
import { Form, Button } from "semantic-ui-react";
import ConditionalBanner from "./ConditionalBanner";
function Edit(props) {
  //conditional validation error display
  const [banner, toggleBanner] = useState(false);
  //conditional server error display
  const [errorBanner, toggleErrorBanner] = useState(false);
  //customer refs
  const customerID = useRef(null);
  const licenseNumber = useRef(null);
  const firstName = useRef(null);
  const lastName = useRef(null);
  const loyaltyPoints = useRef(null);
  //inventory/vehicle refs
  const licensePlate = useRef(null);
  const make = useRef(null);
  const model = useRef(null);
  const category = useRef(null);
  //reservation refs
  const reservationID = useRef(null);
  const resCustomerID = useRef(null);
  const resLicensePlate = useRef(null);
  const startDate = useRef(null);
  const endDate = useRef(null);
  const beforeTax = useRef(null);
  const tax = useRef(null);
  const discount = useRef(null);
  const totalPrice = useRef(null);
  //edit and delete methods below
  //could add functionality to prevent edit submission if nothing's changed
  //could separate banner state into one for each window
  //customer edit and delete
  const handleCustomerEdit = (e) => {
    if (
      !(
        licenseNumber.current.value ||
        firstName.current.value ||
        lastName.current.value ||
        loyaltyPoints.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else {
      toggleBanner(false);
      fetch("http://localhost:8080/customers/{" + props.data.customerId + "}", {
        method: "PUT",
        body: JSON.stringify({
          customerId: customerID.current.value,
          firstName: firstName.current.value,
          lastName: lastName.current.value,
          licenseNumber: licenseNumber.current.value,
          loyaltyPoints: loyaltyPoints.current.value,
        }),
      })
        .then((res) => res.json())
        //implement error handling?
        .then((result) => {
          props.editResults(props.type, result);
          props.goBack(false);
        })
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };
  const handleCustomerDelete = () => {
    toggleBanner(false);
    fetch("http://localhost:8080/customers/{" + props.data.customerId + "}", {
      method: "DELETE",
    })
      .then((res) => res.json())
      //implement error handling? display banner if there is error, return to search results and update if no error
      .then((result) => {
        props.deleteResults(props.type, result.customerId);
        props.goBack(false);
      })
      .catch((err) => {
        console.log("error");
        toggleErrorBanner(true);
      });
  };
  //inventory edit and delete
  const handleInventoryEdit = (e) => {
    if (
      !(
        licensePlate.current.value ||
        category.current.value ||
        make.current.value ||
        model.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else {
      toggleBanner(false);
      fetch(
        "http://localhost:8080/vehicles/{" + props.data.licensePlate + "}",
        {
          method: "PUT",
          body: JSON.stringify({
            licensePlate: licensePlate.current.value,
            category: category.current.value,
            model: model.current.value,
            make: make.current.value,
            //below needed?
            //color: color.current.value
          }),
        }
      )
        .then((res) => res.json())
        //implement error handling?
        .then((result) => {
          props.editResults(props.type, result);
          props.goBack(false);
        })
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };
  const handleInventoryDelete = () => {
    toggleBanner(false);
    fetch("http://localhost:8080/vehicles/{" + props.data.licensePlate + "}", {
      method: "DELETE",
    })
      .then((res) => res.json())
      //implement error handling? display banner if there is error, return to search results and update if no error
      .then((result) => {
        props.deleteResults(props.type, result.customerId);
        props.goBack(false);
      })
      .catch((err) => {
        console.log("error");
        toggleErrorBanner(true);
      });
  };
  //reservation edit and delete
  const handleReservationEdit = (e) => {
    if (
      !(
        resCustomerID.current.value ||
        resLicensePlate.current.value ||
        startDate.current.value ||
        endDate.current.value ||
        beforeTax.current.value ||
        tax.current.value ||
        discount.current.value ||
        totalPrice.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else {
      toggleBanner(false);
      fetch(
        "http://localhost:8080/reservations/{" + props.data.reservationId + "}",
        {
          method: "PUT",
          body: JSON.stringify({
            reservationId: reservationID.current.value,
            customerId: resCustomerID.current.value,
            licensePlate: resLicensePlate.current.value,
            startDate: startDate.current.value,
            endDate: endDate.current.value,
            beforeTax: beforeTax.current.value,
            tax: tax.current.value,
            discount: discount.current.value,
            totalPrice: totalPrice.current.value,
          }),
        }
      )
        .then((res) => res.json())
        //implement error handling?
        .then((result) => {
          props.editResults(props.type, result);
          props.goBack(false);
        })
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };
  const handleReservationDelete = () => {
    toggleBanner(false);
    fetch(
      "http://localhost:8080/reservations/{" + props.data.reservationId + "}",
      {
        method: "DELETE",
      }
    )
      .then((res) => res.json())
      //implement error handling? display banner if there is error, return to search results and update if no error
      .then((result) => {
        props.deleteResults(props.type, result.customerId);
        props.goBack(false);
      })
      .catch((err) => {
        console.log("error");
        toggleErrorBanner(true);
      });
  };
  if (props.type === "customer") {
    <div>
      <ConditionalBanner displayBool={banner} type={"field"} />
      <ConditionalBanner displayBool={errorBanner} type={"error"} />
      <Form>
        <Form.Field>
          <label>Customer ID</label>
          <input
            ref={customerID}
            placeholder="Customer ID"
            value={props.data.customerID}
            readOnly
          />
        </Form.Field>
        <Form.Field>
          <label>License Number</label>
          <input
            ref={licenseNumber}
            placeholder="License Number"
            value={props.data.licenseNumber}
          />
        </Form.Field>
        <Form.Field>
          <label>First Name</label>
          <input
            ref={firstName}
            placeholder="First Name"
            value={props.data.firstName}
          />
        </Form.Field>
        <Form.Field>
          <label>Last Name</label>
          <input
            ref={lastName}
            placeholder="Last Name"
            value={props.data.lastName}
          />
        </Form.Field>
        <Form.Field>
          <label>Loyalty Points</label>
          <input
            ref={loyaltyPoints}
            placeholder="Loyalty Points"
            value={props.data.loyaltyPoints}
          />
        </Form.Field>

        <Button type="submit" onClick={handleCustomerEdit}>
          Submit
        </Button>
        <Button onClick={handleCustomerDelete}>Delete</Button>
      </Form>
    </div>;
  } else if (props.type === "inventory") {
    <div>
      <ConditionalBanner displayBool={banner} type={"field"} />
      <ConditionalBanner displayBool={errorBanner} type={"error"} />
      <Form>
        <Form.Field>
          <label>License Plate</label>
          <input
            ref={licensePlate}
            placeholder="License Number"
            value={props.data.licensePlate}
          />
        </Form.Field>
        <Form.Field>
          <label>Category</label>
          <input
            ref={category}
            placeholder="Category"
            value={props.data.category}
            readOnly
          />
        </Form.Field>
        <Form.Field>
          <label>Make</label>
          <input ref={make} placeholder="Make" value={props.data.make} />
        </Form.Field>
        <Form.Field>
          <label>Model</label>
          <input ref={model} placeholder="Model" value={props.data.model} />
        </Form.Field>

        <Button type="submit" onClick={handleInventoryEdit}>
          Submit
        </Button>
        <Button onClick={handleInventoryDelete}>Delete</Button>
      </Form>
    </div>;
  } else if (props.type === "reservation") {
    <div>
      <ConditionalBanner displayBool={banner} type={"field"} />
      <ConditionalBanner displayBool={errorBanner} type={"error"} />
      <Form>
        <Form.Field>
          <label>Reservation ID</label>
          <input
            ref={reservationID}
            placeholder="Reservation ID"
            value={props.data.reservationId}
            readOnly
          />
        </Form.Field>
        <Form.Field>
          <label>Customer ID</label>
          <input
            ref={resCustomerID}
            placeholder="Customer ID"
            value={props.data.customerId}
          />
        </Form.Field>
        <Form.Field>
          <label>Start Date</label>
          <input
            ref={startDate}
            placeholder="Start Date"
            value={props.data.startDate}
          />
        </Form.Field>
        <Form.Field>
          <label>End Date</label>
          <input
            ref={endDate}
            placeholder="End Date"
            value={props.data.endDate}
          />
        </Form.Field>
        <Form.Field>
          <label>Base Price</label>
          <input
            ref={beforeTax}
            placeholder="Base Price"
            value={props.data.beforeTax}
          />
        </Form.Field>
        <Form.Field>
          <label>Tax</label>
          <input ref={tax} placeholder="Tax" value={props.data.tax} />
        </Form.Field>
        <Form.Field>
          <label>Discount</label>
          <input
            ref={discount}
            placeholder="Discount"
            value={props.data.discount}
          />
        </Form.Field>
        <Form.Field>
          <label>Total Price</label>
          <input
            ref={totalPrice}
            placeholder="Total Price"
            value={props.data.totalPrice}
          />
        </Form.Field>

        <Button type="submit" onClick={handleReservationEdit}>
          Submit
        </Button>
        <Button onClick={handleReservationDelete}>Delete</Button>
      </Form>
    </div>;
  }
}

export default Edit;
