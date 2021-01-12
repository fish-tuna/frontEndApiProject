import { useRef, useState } from "react";
import { Dropdown, Form, Button } from "semantic-ui-react";
import ConditionalBanner from "./ConditionalBanner";
import SearchResults from "./SearchResults";

function SearchFields(props) {
  //store input refs to determine whether or not they're filled in; don't allow any form to submit without at least one field filled in
  //customer refs
  const customerID = useRef(null);
  const licenseNumber = useRef(null);
  const firstName = useRef(null);
  const lastName = useRef(null);
  //inventory refs
  const category = useRef(null);
  const model = useRef(null);
  const make = useRef(null);
  const color = useRef(null);
  //reservation refs
  const resLicenseNumber = useRef(null);
  const resCustomerID = useRef(null);
  const startDate = useRef(null);
  const endDate = useRef(null);
  //error banner status
  const [banner, toggleBanner] = useState(false);
  //bad date format banner
  const [dateBanner, toggleDateBanner] = useState(false);
  //conditional display of search results component
  const [searchResults, setSearchResults] = useState(null);
  //handle customer submit button
  const handleCustomerSearch = (e) => {
    if (
      !(
        customerID.current.value ||
        licenseNumber.current.value ||
        firstName.current.value ||
        lastName.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else {
      toggleBanner(false);
      fetch("http://localhost:8080/customers/find", {
        method: "POST",
        body: JSON.stringify({
          customerId: customerID.current.value,
          firstName: firstName.current.value,
          lastName: lastName.current.value,
          licenseNumber: licenseNumber.current.value,
        }),
      })
        .then((res) => res.json())
        .then((result) => setSearchResults(result))
        .catch((err) => console.log("error"));
    }
  };
  //handle inventory submit button
  const handleInventorySearch = (e) => {
    if (
      !(
        category.current.state.value ||
        model.current.value ||
        make.current.value ||
        color.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else {
      toggleBanner(false);
      fetch("http://localhost:8080/vehicles/find", {
        method: "POST",
        body: JSON.stringify({
          category: category.current.state.value,
          model: model.current.value,
          make: make.current.value,
          color: color.current.value,
        }),
      })
        .then((res) => res.json())
        .then((result) => setSearchResults(result))
        .catch((err) => console.log("error"));
    }
  };
  //handle reservation submit button
  const handleReservationSearch = (e) => {
    if (
      !(
        resLicenseNumber.current.value ||
        resCustomerID.current.value ||
        startDate.current.value ||
        endDate.current.value
      )
    ) {
      e.preventDefault();
      toggleBanner(true);
    } else if (
      !(
        startDate.current.value.match(/^\d{2}\/{1}\d{2}\/{1}\d{2}$/) &&
        endDate.current.value.match(/^\d{2}\/{1}\d{2}\/{1}\d{2}$/)
      )
    ) {
      e.preventDefault();
      toggleDateBanner(true);
    } else {
      toggleBanner(false);
      toggleDateBanner(false);
      fetch("http://localhost:8080/reservations/find", {
        method: "POST",
        body: JSON.stringify({
          licenseNumber: resLicenseNumber.current.value,
          customerId: resCustomerID.current.value,
          startDate: startDate.current.value,
          endDate: endDate.current.value,
        }),
      })
        .then((res) => res.json())
        .then((result) => setSearchResults(result))
        .catch((err) => console.log("error"));
    }
  };

  //conditional rendering
  if (props.field === undefined) {
    return <div></div>;
  } else if (searchResults) {
    return (
      <div>
        <Button onClick={() => setSearchResults(false)}>New Search</Button>
        <SearchResults results={searchResults} type={props.field} />
      </div>
    );
  } else if (props.field === "customer") {
    return (
      <div>
        <ConditionalBanner displayBool={banner} type={"field"} />
        <Form>
          <Form.Field>
            <label>Customer ID</label>
            <input ref={customerID} placeholder="Customer ID" />
          </Form.Field>
          <Form.Field>
            <label>License Number</label>
            <input ref={licenseNumber} placeholder="License Number" />
          </Form.Field>
          <Form.Field>
            <label>First Name</label>
            <input ref={firstName} placeholder="First Name" />
          </Form.Field>
          <Form.Field>
            <label>Last Name</label>
            <input ref={lastName} placeholder="Last Name" />
          </Form.Field>

          <Button type="submit" onClick={handleCustomerSearch}>
            Submit
          </Button>
        </Form>
      </div>
    );
  } else if (props.field === "inventory") {
    return (
      <div>
        <ConditionalBanner display={banner} type={"field"} />
        <Form>
          <Form.Field>
            <label>Category</label>
            <Dropdown
              ref={category}
              placeholder="Select Category"
              fluid
              selection
              options={[
                { text: "Compact", value: "Compact" },
                { text: "Luxury", value: "Luxury" },
              ]}
            />
          </Form.Field>
          <Form.Field>
            <label>Model</label>
            <input ref={model} placeholder="Model" />
          </Form.Field>
          <Form.Field>
            <label>Make</label>
            <input ref={make} placeholder="Make" />
          </Form.Field>
          <Form.Field>
            <label>Color</label>
            <input ref={color} placeholder="Color" />
          </Form.Field>

          <Button type="submit" onClick={handleInventorySearch}>
            Submit
          </Button>
          <Button onClick={console.log(category)}></Button>
        </Form>
      </div>
    );
  } else if (props.field === "reservation") {
    return (
      <div>
        <ConditionalBanner display={banner} type={"field"} />
        <ConditionalBanner display={dateBanner} type={"date"} />
        <Form>
          <Form.Field>
            <label>Vehicle License Number</label>
            <input
              ref={resLicenseNumber}
              placeholder="Vehicle License Number"
            />
          </Form.Field>
          <Form.Field>
            <label>Customer ID</label>
            <input ref={resCustomerID} placeholder="Customer ID" />
          </Form.Field>
          <Form.Field>
            <label>Start Date</label>
            <input ref={startDate} placeholder="Start Date" />
          </Form.Field>
          <Form.Field>
            <label>End Date</label>
            <input ref={endDate} placeholder="End Date" />
          </Form.Field>

          <Button type="submit" onClick={handleReservationSearch}>
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}

export default SearchFields;
