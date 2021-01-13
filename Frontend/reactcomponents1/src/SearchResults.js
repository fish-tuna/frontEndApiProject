import { useState } from "react";
import { Table, Button } from "semantic-ui-react";
import Edit from "./Edit";

function SearchResults(props) {
  const [searchResults, setSearchResults] = useState(props.searchResults);
  const [displayEditPane, toggleDisplayEditPane] = useState(false);
  const [selectedData, setSelectedData] = useState(null);

  //edit search results (function to be passed to Edit component)
  const editResults = (type, editedResult) => {
    if (type === "customer") {
      setSearchResults(
        searchResults.map(
          (i) =>
            (i = i.customerId === editedResult.customerId ? editedResult : i)
        )
      );
    } else if (type === "inventory") {
      setSearchResults(
        searchResults.map(
          (i) =>
            (i =
              i.licensePlate === editedResult.licensePlate ? editedResult : i)
        )
      );
    } else if (type === "reservation") {
      setSearchResults(
        searchResults.map(
          (i) =>
            (i =
              i.reservationId === editedResult.reservationId ? editedResult : i)
        )
      );
    }
  };
  //delete search results (function to be passed to Edit component)
  const deleteResults = (type, id) => {
    if (type === "customer") {
      setSearchResults(searchResults.filter((i) => i.customerId !== id));
    } else if (type === "inventory") {
      setSearchResults(searchResults.filter((i) => i.licensePlate !== id));
    } else if (type === "reservation") {
      setSearchResults(searchResults.filter((i) => i.reservationId !== id));
    }
  };
  //handle empty results, this conditional probably needs to be modified to fit error message
  if (!props.searchResults) {
    return (
      <div>
        <h1>No Results</h1>
      </div>
    );
  } else if (displayEditPane) {
    return (
      <div>
        <Edit
          type={props.type}
          data={selectedData}
          editResults={editResults}
          deleteResults={deleteResults}
          goBack={toggleDisplayEditPane}
        />
      </div>
    );
  } else if (props.type === "customer") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>Customer ID</Table.HeaderCell>
              <Table.HeaderCell>First Name</Table.HeaderCell>
              <Table.HeaderCell>Last Name</Table.HeaderCell>
              <Table.HeaderCell>Date of Birth</Table.HeaderCell>
              <Table.HeaderCell>License Number</Table.HeaderCell>
              <Table.HeaderCell>Loyalty Points</Table.HeaderCell>
              <Table.HeaderCell>{/*empty*/}</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {searchResults.map((result) => (
              <Table.Row key={result.customerId}>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.firstName}</Table.Cell>
                <Table.Cell>{result.lastName}</Table.Cell>
                <Table.Cell>{result.dateOfBirth}</Table.Cell>
                <Table.Cell>{result.licenseNumber}</Table.Cell>
                <Table.Cell>{result.loyaltyPoints}</Table.Cell>
                <Table.Cell>
                  <Button
                    onClick={() => {
                      setSelectedData(result);
                      toggleDisplayEditPane(true);
                    }}
                  ></Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
  //placeholder, needs implementation
  else if (props.type === "inventory") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>License Plate</Table.HeaderCell>
              <Table.HeaderCell>Category</Table.HeaderCell>
              <Table.HeaderCell>Model</Table.HeaderCell>
              <Table.HeaderCell>Make</Table.HeaderCell>
              <Table.HeaderCell>{/*empty*/}</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {searchResults.map((result) => (
              <Table.Row key={result.licensePlate}>
                <Table.Cell>{result.licensePlate}</Table.Cell>
                <Table.Cell>{result.category}</Table.Cell>
                <Table.Cell>{result.make}</Table.Cell>
                <Table.Cell>{result.model}</Table.Cell>

                <Table.Cell>
                  <Button
                    onClick={() => {
                      setSelectedData(result);
                      toggleDisplayEditPane(true);
                    }}
                  ></Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
  //placeholder, needs implementation
  else if (props.type === "reservation") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>Reservation ID</Table.HeaderCell>
              <Table.HeaderCell>Customer ID</Table.HeaderCell>
              <Table.HeaderCell>License Plate</Table.HeaderCell>
              <Table.HeaderCell>Start Date</Table.HeaderCell>
              <Table.HeaderCell>End Date</Table.HeaderCell>
              <Table.HeaderCell>Base Price</Table.HeaderCell>
              <Table.HeaderCell>Tax</Table.HeaderCell>
              <Table.HeaderCell>Discount</Table.HeaderCell>
              <Table.HeaderCell>Total Price</Table.HeaderCell>
              <Table.HeaderCell>{/*empty*/}</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {searchResults.map((result) => (
              <Table.Row key={result.reservationId}>
                <Table.Cell>{result.reservationId}</Table.Cell>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.licensePlate}</Table.Cell>
                <Table.Cell>{result.startDate}</Table.Cell>
                <Table.Cell>{result.endDate}</Table.Cell>
                <Table.Cell>{result.beforeTax}</Table.Cell>
                <Table.Cell>{result.tax}</Table.Cell>
                <Table.Cell>{result.discount}</Table.Cell>
                <Table.Cell>{result.totalPrice}</Table.Cell>
                <Table.Cell>
                  <Button
                    onClick={() => {
                      setSelectedData(result);
                      toggleDisplayEditPane(true);
                    }}
                  ></Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
}

export default SearchResults;
