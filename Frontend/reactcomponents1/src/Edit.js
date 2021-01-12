import { useState, useRef } from "react";
import { Form, Button } from "semantic-ui-react";
import ConditionalBanner from "./ConditionalBanner";
//need to implement one of two things: go back to updated search results upon successful edit/delete,
//or go back to search homepage after successful edit/delete. The latter would take much less time to implement.
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
      fetch("http://localhost:8080/customers/{" + props.data.customerID + "}", {
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
        //!!! implement error handling
        .then((result) => {
          /*go back*/
        })
        .catch((err) => {
          console.log("error");
          toggleErrorBanner(true);
        });
    }
  };
  const handleCustomerDelete = () => {
    toggleBanner(false);
    fetch("http://localhost:8080/customers/{" + props.data.customerID + "}", {
      method: "DELETE",
    })
      .then((res) => res.json())
      //!!! implement error handling. display banner if there is error, return to search results and update if no error
      .then((result) => {
        /*go back*/
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
  }
}

export default Edit;
