import { Table } from "semantic-ui-react";

function SearchResults(props) {
  //will server return some kind of error message upon failed search that will require this if statement to be revised?
  if (props.searchResults) {
    return (
      <div>
        <h1>No Results</h1>
      </div>
    );
  } else if (props.type == "customer") {
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
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {props.searchResults.map((result) => (
              <Table.Row key={result.customerId}>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.firstName}</Table.Cell>
                <Table.Cell>{result.lastName}</Table.Cell>
                <Table.Cell>{result.dateOfBirth}</Table.Cell>
                <Table.Cell>{result.licenseNumber}</Table.Cell>
                <Table.Cell>{result.loyaltyPoints}</Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
}

export default SearchResults;
