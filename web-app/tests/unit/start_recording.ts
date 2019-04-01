import * as sinon from 'sinon'
import * as chai from 'chai'
import * as request from 'request'

const apiURL = process.env.VUE_APP_API_URL;

describe('Setting up test variables', () => {
  beforeEach(() => {
    const responseObject = {
      statusCode: 200,
      headers: {
        'content-type': 'application/json'
      }
    };
    const responseBody = {
      "message": "You're now recording! \n Recording started at : 1553874543515",
      "data": {
        "connected": true,
        "recording": true,
        "stopped": false,
        "startedAt": 1553874543515,
        "recorded": 0,
        "scenes": []
      }
    };
    this.post = sinon.stub(request, 'post');
  });
  afterEach(() => {
    request.restore();
  });

  describe('Test GET api/obs/recording/start', () => {
    it('Verify that start button actually starts the recording', function () {
      this.post.yields(null, responseObject, JSON.stringify(responseBody));
      request.get(`${apiURL}/api/v1/movies`, (err, res, body) => {
        // there should be a 200 status code
        res.statusCode.should.eql(200);
        // the response should be JSON
        res.headers['content-type'].should.contain('application/json');
        // parse response body
        body = JSON.parse(body);
        // the JSON response body should have a
        // key-value pair of {"status": "success"}
        body.status.should.eql('success');
        // the JSON response body should have a
        // key-value pair of {"data": [3 movie objects]}
        body.data.length.should.eql(3);
        // the first object in the data array should
        // have the right keys
        body.data[0].should.include.keys(
          'id', 'name', 'genre', 'rating', 'explicit'
        );
        // the first object should have the right value for name
        body.data[0].name.should.eql('The Land Before Time');
        done();
      });
    })
  });
});
