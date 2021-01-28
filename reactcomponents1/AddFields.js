import { useRef, useState } from "react";
import { Segment, Dropdown, Form, Button } from "semantic-ui-react";
import ConditionalBanner from "./ConditionalBanner";

function AddFields(props) {
  //store input refs to determine whether or not they're filled in; don't allow any form to submit without at least one field filled in
  //customer refs
  const dateOfBirth = useRef(null);
  const licenseNumber = useRef(null);
  const firstName = useRef(null);
  const lastName = useRef(null);
  //inventory refs
  const category = useRef(null);
  const model = useRef(null);
  const make = useRef(null);
  const color = useRef(null);
  const licenseNumber = useRef(null);
  //reservation refs
  const resLicenseNumber = useRef(null);
  const resCustomerID = useRef(null);
  const startDate = useRef(null);
  const endDate = useRef(null);
  const discount = useRef(null);
  const tax = useRef(null);
  //server error banner status
  const [errorBanner, toggleErrorBanner] = useState(false);
  const [successBanner, toggleSuccessBanner] = useState(false);
  //bad date format banner
  const [dateBanner, toggleDateBanner] = useState(false);
  const [birthDateBanner, toggleBirthDateBanner] = useState(false);
  //store reservation or customer ID from server
  const [returnedReservationID, setReturnedReservationID] = useState(null);
  const [returnedCustomerID, setReturnedCustomerID] = useState(null);
  //handle customer submit button
  const handleAddCustomer = (e) => {
    if (!dateOfBirth.current.value.match(/^\d{2}\/{1}\d{2}\/{1}\d{2}$/)) {
      e.preventDefault();
      toggleBirthDateBanner(true);
    } else {
      e.preventDefault();
      toggleBanner(false);
      fetch("http://localhost:8080/customers", {
        method: "POST",
        body: JSON.stringify({
          dateOfBirth: dateOfBirth.current.value,
          firstName: firstName.current.value,
          lastName: lastName.current.value,
          licenseNumber: licenseNumber.current.value,
        }),
      })
        .then((res) => res.json())
        //make sure this works with JSON object from server
        .then((result) => setReturnedCustomerID(result.customerId))
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };
  //handle inventory submit button
  const handleAddInventory = (e) => {
    e.preventDefault();
    toggleBanner(false);
    fetch("http://localhost:8080/vehicles", {
      method: "POST",
      body: JSON.stringify({
        category: category.current.state.value,
        model: model.current.value,
        make: make.current.value,
        color: color.current.value,
        licenseNumber: licenseNumber.current.value,
      }),
    })
      .then((res) => res.json())
      .then((result) => toggleSuccessBanner(true))
      .catch((err) => {
        console.log("error");
        toggleErrorBanner(true);
      });
  };
  //handle reservation submit button
  const handleAddReservation = (e) => {
    if (
      !(
        startDate.current.value.match(/^\d{2}\/{1}\d{2}\/{1}\d{2}$/) &&
        endDate.current.value.match(/^\d{2}\/{1}\d{2}\/{1}\d{2}$/)
      )
    ) {
      e.preventDefault();
      toggleDateBanner(true);
    } else {
      e.preventDefault();
      toggleBanner(false);
      toggleDateBanner(false);
      fetch("http://localhost:8080/reservations", {
        method: "POST",
        body: JSON.stringify({
          licenseNumber: resLicenseNumber.current.value,
          customerId: resCustomerID.current.value,
          startDate: startDate.current.value,
          endDate: endDate.current.value,
          discount: discount.current.value,
          tax: tax.current.value,
        }),
      })
        .then((res) => res.json())
        //confirm that result.reservationId is correct for the JSON object received from server
        .then((result) => setReturnedReservationID(result.reservationId))
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };

  //conditional rendering
  if (props.field === undefined) {
    return <div></div>;
  } else if (props.field === "customer") {
    return (
      <div>
        <ConditionalBanner display={birthDateBanner} type={"date"} />
        <ConditionalBanner displayBool={errorBanner} type={"error"} />
        <Form>
          <Form.Field>
            <label>Date of Birth</label>
            <input
              ref={dateOfBirth}
              placeholder="Date of Birth (MM/DD/YY)"
              required
            />
          </Form.Field>
          <Form.Field>
            <label>License Number</label>
            <input ref={licenseNumber} placeholder="License Number" required />
          </Form.Field>
          <Form.Field>
            <label>First Name</label>
            <input ref={firstName} placeholder="First Name" required />
          </Form.Field>
          <Form.Field>
            <label>Last Name</label>
            <input ref={lastName} placeholder="Last Name" required />
          </Form.Field>

          <Button type="submit" onClick={handleAddCustomer}>
            Submit
          </Button>
          <Segment>Created Customer ID: {returnedCustomerID}</Segment>
        </Form>
      </div>
    );
  } else if (props.field === "inventory") {
    return (
      <div>
        <ConditionalBanner displayBool={successBanner} type={"success"} />
        <ConditionalBanner displayBool={errorBanner} type={"error"} />
        <Form>
          <Form.Field>
            <label>License Number</label>
            <input ref={licenseNumber} placeholder="License Number" required />
          </Form.Field>
          <Form.Field>
            <label>Category</label>
            {/*these are placeholder categories. Need to get the actual categories from the backend team */}
            <Dropdown
              ref={category}
              placeholder="Select Category"
              fluid
              selection
              options={[
                { text: "Compact", value: "Compact" },
                { text: "Luxury", value: "Luxury" },
              ]}
              required
            />
          </Form.Field>
          <Form.Field>
            <label>Model</label>
            <input ref={model} placeholder="Model" required />
          </Form.Field>
          <Form.Field>
            <label>Make</label>
            <input ref={make} placeholder="Make" required />
          </Form.Field>
          <Form.Field>
            <label>Color</label>
            <input ref={color} placeholder="Color" required />
          </Form.Field>

          <Button type="submit" onClick={handleAddInventory}>
            Submit
          </Button>
        </Form>
      </div>
    );
  } else if (props.field === "reservation") {
    return (
      <div>
        <ConditionalBanner display={dateBanner} type={"date"} />
        <ConditionalBanner displayBool={errorBanner} type={"error"} />
        <Form>
          <Form.Field>
            <label>Vehicle License Number</label>
            <input
              ref={resLicenseNumber}
              placeholder="Vehicle License Number"
              required
            />
          </Form.Field>
          <Form.Field>
            <label>Customer ID</label>
            <input ref={resCustomerID} placeholder="Customer ID" required />
          </Form.Field>
          <Form.Field>
            <label>Start Date</label>
            <input
              ref={startDate}
              placeholder="Start Date (MM/DD/YY)"
              required
            />
          </Form.Field>
          <Form.Field>
            <label>End Date</label>
            <input ref={endDate} placeholder="End Date (MM/DD/YY)" required />
          </Form.Field>
          <Form.Field>
            <label>Discount</label>
            <input ref={discount} placeholder="Discount" required />
          </Form.Field>
          <Form.Field>
            <label>Tax</label>
            <input ref={tax} placeholder="Tax" required />
          </Form.Field>

          <Button type="submit" onClick={handleAddReservation}>
            Submit
          </Button>
          <Segment>Created Reservation ID: {returnedReservationID}</Segment>
        </Form>
      </div>
    );
  }
}

export default AddFields;
